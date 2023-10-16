package indi.yuluo.governance.routing.gateway.filter.defaults;

import indi.yuluo.governance.routing.gateway.context.LabelRoutingGatewayContext;
import indi.yuluo.governance.routing.gateway.filter.LabelRoutingGatewayClearFilter;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class DefaultLabelRoutingGatewayClearFilter
		implements LabelRoutingGatewayClearFilter {

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE - 1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		LabelRoutingGatewayContext.clearCurrentContext();

		return chain.filter(exchange);
	}

}
