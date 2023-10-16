package indi.yuluo.governance.routing.zuul.constants;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public final class RoutingZuulConstants {

	private RoutingZuulConstants() {
	}

	/**
	 * Service governance traffic stains the public prefix.
	 */
	public static final String ZUUL_PROPERTY_PREFIX = "spring.cloud.governance.routing.zuul";

	/**
	 * zuul filter order.
	 */
	public static final String ZUUL_ROUTE_FILTER_ORDER = ZUUL_PROPERTY_PREFIX
			+ ".filter.order";

	/**
	 * Zuul header priority.
	 */
	public static final String ZUUL_HEADER_PRIORITY = ZUUL_PROPERTY_PREFIX
			+ ".header.priority";

	/**
	 * Zuul filter order value.
	 */
	public static final int ZUUL_ROUTE_FILTER_ORDER_VALUE = 0;

}
