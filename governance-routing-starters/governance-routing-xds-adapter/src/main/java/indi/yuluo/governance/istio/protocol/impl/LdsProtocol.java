package indi.yuluo.governance.istio.protocol.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.protobuf.InvalidProtocolBufferException;
import indi.yuluo.governance.commons.lang.StringUtils;
import indi.yuluo.governance.istio.XdsChannel;
import indi.yuluo.governance.istio.XdsConfigProperties;
import indi.yuluo.governance.istio.XdsScheduledThreadPool;
import indi.yuluo.governance.istio.constant.IstioConstants;
import indi.yuluo.governance.istio.filter.XdsResolveFilter;
import indi.yuluo.governance.istio.protocol.AbstractXdsProtocol;
import io.envoyproxy.envoy.config.listener.v3.Filter;
import io.envoyproxy.envoy.config.listener.v3.Listener;
import io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager;
import io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.Rds;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class LdsProtocol extends AbstractXdsProtocol<Listener> {

	public LdsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool,
			XdsConfigProperties xdsConfigProperties,
			List<XdsResolveFilter<List<Listener>>> ldsFilters) {
		super(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties);
		// init filters
		for (XdsResolveFilter<List<Listener>> filter : ldsFilters) {
			if (IstioConstants.LDS_URL.equals(filter.getTypeUrl())) {
				filters.add(filter);
			}
		}
	}

	@Override
	public String getTypeUrl() {
		return IstioConstants.LDS_URL;
	}

	@Override
	public List<Listener> decodeXdsResponse(DiscoveryResponse response) {
		List<Listener> listeners = new ArrayList<>();
		for (com.google.protobuf.Any res : response.getResourcesList()) {
			try {
				Listener listener = res.unpack(Listener.class);
				if (listener != null) {
					listeners.add(listener);
				}
			}
			catch (Exception e) {
				log.error("Unpack listeners failed", e);
			}
		}
		fireXdsFilters(listeners);
		return listeners;
	}

	@Override
	protected Set<String> resolveResourceNames(List<Listener> resources) {
		Set<String> routeNames = new HashSet<>();
		resources.forEach(listener -> routeNames.addAll(listener.getFilterChainsList()
				.stream().flatMap((e) -> e.getFiltersList().stream())
				.map(Filter::getTypedConfig).map(any -> {
					try {
						if (!any.is(HttpConnectionManager.class)) {
							return null;
						}
						return any.unpack(HttpConnectionManager.class);
					}
					catch (InvalidProtocolBufferException e) {
						return null;
					}
				}).filter(Objects::nonNull).map(HttpConnectionManager::getRds)
				.map(Rds::getRouteConfigName).filter(StringUtils::isNotEmpty)
				.collect(Collectors.toList())));
		return routeNames;

	}

}
