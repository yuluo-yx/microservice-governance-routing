package indi.yuluo.governance.routing.gateway.context.defaults;

import indi.yuluo.governance.routing.context.AbstractLabelRoutingContextHolder;
import indi.yuluo.governance.routing.gateway.context.LabelRoutingGatewayContext;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class DefaultLabelRoutingGatewayContextHolder
		extends AbstractLabelRoutingContextHolder {

	@Override
	public String getLabelRouteRegion() {

		return LabelRoutingGatewayContext.getCurrentContext().getRegion();
	}

	@Override
	public String getLabelRouteZone() {

		return LabelRoutingGatewayContext.getCurrentContext().getZone();
	}

	@Override
	public ServerHttpRequest getServerHttpRequest() {

		return LabelRoutingGatewayContext.getCurrentContext().getServerHttpRequest();
	}

}
