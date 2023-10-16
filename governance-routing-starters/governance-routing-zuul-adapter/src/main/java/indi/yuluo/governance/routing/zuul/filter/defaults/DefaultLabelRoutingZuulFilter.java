package indi.yuluo.governance.routing.zuul.filter.defaults;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.netflix.zuul.context.RequestContext;
import indi.yuluo.governance.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.properties.LabelRoutingProperties;
import indi.yuluo.governance.routing.zuul.constants.RoutingZuulConstants;
import indi.yuluo.governance.routing.zuul.context.LabelRoutingZuulContext;
import indi.yuluo.governance.routing.zuul.filter.LabelRoutingZuulFilter;
import indi.yuluo.governance.routing.zuul.util.LabelRoutingZuulFilterResolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class DefaultLabelRoutingZuulFilter extends LabelRoutingZuulFilter {

	// Zuul filter order.
	@Value("${" + RoutingZuulConstants.ZUUL_ROUTE_FILTER_ORDER + ":"
			+ RoutingZuulConstants.ZUUL_ROUTE_FILTER_ORDER_VALUE + "}")
	protected Integer zuulFilterOrder;

	// Gateway rule priority switch.
	@Value("${" + RoutingZuulConstants.ZUUL_HEADER_PRIORITY + ":true}")
	protected Boolean zuulHeaderPriority;

	@Resource
	private LabelRoutingProperties properties;

	@Override
	public String filterType() {

		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return zuulFilterOrder;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {

		RequestContext context = RequestContext.getCurrentContext();
		applyRequestHeader(context);

		return null;
	}

	private void applyRequestHeader(RequestContext context) {

		// Use map to simplify if... else statement
		Map<String, String> routingPropertiesMap = new HashMap<>();
		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_ZONE,
				properties.getZone());
		LabelRoutingZuulContext.getCurrentContext().setZone(properties.getZone());
		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_REGION,
				properties.getRegion());
		LabelRoutingZuulContext.getCurrentContext().setRegion(properties.getRegion());

		LabelRoutingZuulContext.getCurrentContext()
				.setHttpServletRequest(context.getRequest());

		routingPropertiesMap.forEach((k, v) -> LabelRoutingZuulFilterResolver
				.setHeader(context, k, v, zuulHeaderPriority));

	}

}
