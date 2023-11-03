package indi.yuluo.governance.istio.protocol.impl;

import java.util.ArrayList;
import java.util.List;

import indi.yuluo.governance.istio.XdsChannel;
import indi.yuluo.governance.istio.XdsConfigProperties;
import indi.yuluo.governance.istio.XdsScheduledThreadPool;
import indi.yuluo.governance.istio.constant.IstioConstants;
import indi.yuluo.governance.istio.protocol.AbstractXdsProtocol;
import io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class EdsProtocol extends AbstractXdsProtocol<ClusterLoadAssignment> {

	public EdsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool,
			XdsConfigProperties xdsConfigProperties) {
		super(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties);
	}

	@Override
	protected List<ClusterLoadAssignment> decodeXdsResponse(DiscoveryResponse response) {
		List<ClusterLoadAssignment> endpoints = new ArrayList<>();
		for (com.google.protobuf.Any res : response.getResourcesList()) {
			try {
				ClusterLoadAssignment endpoint = res.unpack(ClusterLoadAssignment.class);
				endpoints.add(endpoint);
			}
			catch (Exception e) {
				log.error("Unpack cluster failed", e);
			}
		}
		fireXdsFilters(endpoints);
		return endpoints;
	}

	@Override
	public String getTypeUrl() {
		return IstioConstants.EDS_URL;
	}

}
