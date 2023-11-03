package indi.yuluo.governance.istio.protocol.impl;

import java.util.ArrayList;
import java.util.List;

import indi.yuluo.governance.istio.XdsChannel;
import indi.yuluo.governance.istio.XdsConfigProperties;
import indi.yuluo.governance.istio.XdsScheduledThreadPool;
import indi.yuluo.governance.istio.constant.IstioConstants;
import indi.yuluo.governance.istio.filter.XdsResolveFilter;
import indi.yuluo.governance.istio.protocol.AbstractXdsProtocol;
import io.envoyproxy.envoy.config.route.v3.RouteConfiguration;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class RdsProtocol extends AbstractXdsProtocol<RouteConfiguration> {

	public RdsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool,
			XdsConfigProperties xdsConfigProperties,
			List<XdsResolveFilter<List<RouteConfiguration>>> rdsFilters) {
		super(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties);
		for (XdsResolveFilter<List<RouteConfiguration>> filter : rdsFilters) {
			if (IstioConstants.RDS_URL.equals(filter.getTypeUrl())) {
				filters.add(filter);
			}
		}
	}

	@Override
	public List<RouteConfiguration> decodeXdsResponse(DiscoveryResponse response) {
		List<RouteConfiguration> routes = new ArrayList<>();
		for (com.google.protobuf.Any res : response.getResourcesList()) {
			try {
				RouteConfiguration route = res.unpack(RouteConfiguration.class);
				routes.add(route);
			}
			catch (Exception e) {
				log.error("Unpack cluster failed", e);
			}
		}
		fireXdsFilters(routes);
		return routes;
	}

	@Override
	public String getTypeUrl() {
		return IstioConstants.RDS_URL;
	}

}
