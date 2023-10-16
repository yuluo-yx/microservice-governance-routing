package indi.yuluo.governance.commons.governance.routing;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class UnifiedRoutingDataStructure {

	private RoutingRule routingRule;

	private String targetService;

	public RoutingRule getLabelRouteRule() {
		return routingRule;
	}

	public void setLabelRouteRule(RoutingRule labelRouteRule) {
		this.routingRule = labelRouteRule;
	}

	public String getTargetService() {
		return targetService;
	}

	public void setTargetService(String targetService) {
		this.targetService = targetService;
	}

	@Override
	public String toString() {
		return "UntiedRoutingDataStructure{" + "RoutingData=" + routingRule
				+ ", targetService='" + targetService + '\'' + '}';
	}

}
