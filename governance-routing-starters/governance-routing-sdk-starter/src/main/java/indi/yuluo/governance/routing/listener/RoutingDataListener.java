package indi.yuluo.governance.routing.listener;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.cloud.commons.governance.event.RoutingDataChangedEvent;
import com.alibaba.cloud.commons.governance.routing.UnifiedRoutingDataStructure;
import indi.yuluo.governance.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.repository.FilterService;
import indi.yuluo.governance.routing.repository.RoutingDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@Order(LabelRoutingConstants.LISTENER_ORDER)
public class RoutingDataListener implements ApplicationListener<RoutingDataChangedEvent> {

	private static final Logger logger = LoggerFactory
			.getLogger(RoutingDataListener.class);

	private final RoutingDataRepository routingDataRepository;

	private final FilterService filterService;

	private List<UnifiedRoutingDataStructure> routeDatalist;

	private HashSet<String> definitionService;

	public RoutingDataListener(RoutingDataRepository routeDataRepository,
			FilterService filterService) {

		this.routingDataRepository = routeDataRepository;
		this.filterService = filterService;
	}

	@Override
	public void onApplicationEvent(RoutingDataChangedEvent event) {
		try {
			Collection<UnifiedRoutingDataStructure> unifiedRoutingDataStructureList = event
					.getUntiedRouterDataStructureList();

			// Filter service.
			// todo can cache the result
			definitionService = filterService
					.getDefinitionService(unifiedRoutingDataStructureList.size());

			routeDatalist = unifiedRoutingDataStructureList.stream()
					.filter(unifiedRouteDataStructure -> definitionService
							.contains(unifiedRouteDataStructure.getTargetService()))
					.collect(Collectors.toList());

			if (routeDatalist.isEmpty()) {
				routeDatalist.addAll(unifiedRoutingDataStructureList);
			}

			routingDataRepository.updateRouteData(routeDatalist);
		}
		catch (Exception e) {
			logger.error("Failed to update route data", e);
		}
	}

}
