package indi.yuluo.governance.istio.protocol.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import indi.yuluo.governance.istio.XdsChannel;
import indi.yuluo.governance.istio.XdsConfigProperties;
import indi.yuluo.governance.istio.XdsScheduledThreadPool;
import indi.yuluo.governance.istio.constant.IstioConstants;
import indi.yuluo.governance.istio.protocol.AbstractXdsProtocol;
import io.envoyproxy.envoy.config.cluster.v3.Cluster;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class CdsProtocol extends AbstractXdsProtocol<Cluster> {

	public CdsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool,
			XdsConfigProperties xdsConfigProperties) {
		super(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties);
	}

	@Override
	protected List<Cluster> decodeXdsResponse(DiscoveryResponse response) {
		List<Cluster> clusters = new ArrayList<>();
		for (com.google.protobuf.Any res : response.getResourcesList()) {
			try {
				Cluster cluster = res.unpack(Cluster.class);
				clusters.add(cluster);
			}
			catch (Exception e) {
				log.error("Unpack cluster failed", e);
			}
		}
		fireXdsFilters(clusters);
		return clusters;
	}

	@Override
	protected Set<String> resolveResourceNames(List<Cluster> resources) {
		Set<String> endpoints = new HashSet<>();
		if (resources == null) {
			return endpoints;
		}
		for (Cluster cluster : resources) {
			cluster.getEdsClusterConfig().getServiceName();
			endpoints.add(cluster.getEdsClusterConfig().getServiceName());
		}
		return endpoints;
	}

	@Override
	public String getTypeUrl() {
		return IstioConstants.CDS_URL;
	}

}
