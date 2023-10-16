package indi.yuluo.governance.routing.gateway.constants;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public final class LabelRoutingGatewayConstants {

	private LabelRoutingGatewayConstants() {
	}

	/**
	 * Service governance traffic stains the public prefix.
	 */
	public static final String GATEWAY_PROPERTY_PREFIX = "spring.cloud.governance.routing.gateway";

	/**
	 * Strategy gateway route filter order.
	 */
	public static final String GATEWAY_ROUTE_FILTER_ORDER = GATEWAY_PROPERTY_PREFIX
			+ ".filter.order";

	/**
	 * Filter order number.
	 */
	public static final int GATEWAY_ROUTE_FILTER_ORDER_VALUE = 10000;

	/**
	 * Whether strategy gateway header priority is enabled.
	 */
	public static final String GATEWAY_HEADER_PRIORITY = GATEWAY_PROPERTY_PREFIX
			+ ".header.priority";

}
