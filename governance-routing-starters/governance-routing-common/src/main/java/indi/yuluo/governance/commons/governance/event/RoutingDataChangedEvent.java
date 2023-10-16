package indi.yuluo.governance.commons.governance.event;

import java.util.Collection;

import indi.yuluo.governance.commons.governance.routing.UnifiedRoutingDataStructure;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class RoutingDataChangedEvent extends GovernanceEvent {

	/**
	 * Configuration for Label Routing.
	 */
	private final Collection<UnifiedRoutingDataStructure> untiedRouterDataStructureList;

	public RoutingDataChangedEvent(Object source,
			Collection<UnifiedRoutingDataStructure> untiedRouterDataStructureList) {
		super(source);
		this.untiedRouterDataStructureList = untiedRouterDataStructureList;
	}

	public Collection<UnifiedRoutingDataStructure> getUntiedRouterDataStructureList() {
		return untiedRouterDataStructureList;
	}

}
