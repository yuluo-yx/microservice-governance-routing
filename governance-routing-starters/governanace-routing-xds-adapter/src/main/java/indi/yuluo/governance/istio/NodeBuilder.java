package indi.yuluo.governance.istio;

import java.net.InetAddress;
import java.net.UnknownHostException;

import indi.yuluo.governance.istio.constant.IstioConstants;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import io.envoyproxy.envoy.config.core.v3.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public final class NodeBuilder {

	private static final Logger log = LoggerFactory.getLogger(NodeBuilder.class);

	private static Node NODE;

	private NodeBuilder() {

	}

	public static Node getNode() {
		try {
			if (NODE != null) {
				return NODE;
			}
			String podName = System.getenv(IstioConstants.POD_NAME);
			if (podName == null) {
				podName = IstioConstants.DEFAULT_POD_NAME;
			}
			String podNamespace = System.getenv(IstioConstants.NAMESPACE_NAME);
			if (podNamespace == null) {
				podNamespace = IstioConstants.DEFAULT_NAMESPACE;
			}
			String ip = "127.0.0.1";
			try {
				InetAddress local = InetAddress.getLocalHost();
				ip = local.getHostAddress();
			}
			catch (UnknownHostException e) {
				log.error("Can not get local ip", e);
			}
			Struct.Builder metaBuilder = Struct.newBuilder();
			// metadata is necessary for RequestAuthentication CRD
			metaBuilder.putFields("NAMESPACE",
					Value.newBuilder().setStringValue(podNamespace).build());
			NODE = Node.newBuilder()
					.setId(String.format(
							"sidecar~%s~%s.%s~%s" + IstioConstants.SVC_CLUSTER_LOCAL, ip,
							podName, podNamespace, podNamespace))
					.setMetadata(metaBuilder.build()).build();
			return NODE;
		}
		catch (Exception e) {
			log.error("Unable to create node for xds request", e);
		}
		return null;
	}

}
