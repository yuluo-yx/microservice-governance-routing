package indi.yuluo.governance.istio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import indi.yuluo.governance.commons.io.FileUtils;
import indi.yuluo.governance.commons.lang.StringUtils;
import indi.yuluo.governance.istio.constant.IstioConstants;
import io.envoyproxy.envoy.service.discovery.v3.AggregatedDiscoveryServiceGrpc;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryRequest;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class XdsChannel implements AutoCloseable {

	private static final Logger log = LoggerFactory.getLogger(XdsChannel.class);

	private ManagedChannel channel;

	private String istiodToken;

	private final XdsConfigProperties xdsConfigProperties;

	public XdsChannel(XdsConfigProperties xdsConfigProperties) {
		this.xdsConfigProperties = xdsConfigProperties;
		try {
			if (xdsConfigProperties.getPort() == IstioConstants.ISTIOD_SECURE_PORT) {
				// fetch token first
				if (StringUtils.isNotEmpty(xdsConfigProperties.getIstiodToken())) {
					istiodToken = xdsConfigProperties.getIstiodToken();
				}
				else {
					this.refreshIstiodToken();
				}
				SslContext sslcontext = GrpcSslContexts.forClient()
						// if server's cert doesn't chain to a standard root
						.trustManager(InsecureTrustManagerFactory.INSTANCE)
						// TODO: fill the publicKey and privateKey
						.build();
				this.channel = NettyChannelBuilder
						.forTarget(xdsConfigProperties.getHost() + ":"
								+ xdsConfigProperties.getPort())
						.negotiationType(NegotiationType.TLS).sslContext(sslcontext)
						.build();
			}
			else {
				this.channel = NettyChannelBuilder
						.forTarget(xdsConfigProperties.getHost() + ":"
								+ xdsConfigProperties.getPort())
						.negotiationType(NegotiationType.PLAINTEXT).build();
			}
		}
		catch (Exception e) {
			log.error("Create XdsChannel failed", e);
		}
	}

	public void refreshIstiodToken() {
		if (xdsConfigProperties.getPort() != IstioConstants.ISTIOD_SECURE_PORT) {
			return;
		}
		File saFile = new File(IstioConstants.THIRD_PART_JWT_PATH);
		if (saFile.canRead()) {
			try {
				this.istiodToken = FileUtils.readFileToString(saFile,
						StandardCharsets.UTF_8);
				return;
			}
			catch (IOException e) {
				log.error("Unable to read token file", e);
			}
		}
		if (this.istiodToken == null) {
			throw new UnsupportedOperationException(
					"Unable to found kubernetes service account token file. "
							+ "Please check if work in Kubernetes and mount service account token file correctly.");
		}
	}

	@Override
	public void close() {
		if (channel != null) {
			channel.shutdown();
		}
	}

	public StreamObserver<DiscoveryRequest> createDiscoveryRequest(
			StreamObserver<DiscoveryResponse> observer) {
		if (channel == null) {
			return null;
		}
		AggregatedDiscoveryServiceGrpc.AggregatedDiscoveryServiceStub stub = AggregatedDiscoveryServiceGrpc
				.newStub(channel);
		Metadata header = new Metadata();
		Metadata.Key<String> key = Metadata.Key.of("authorization",
				Metadata.ASCII_STRING_MARSHALLER);
		header.put(key, "Bearer " + this.istiodToken);
		stub = MetadataUtils.attachHeaders(stub, header);
		return stub.streamAggregatedResources(observer);
	}

}
