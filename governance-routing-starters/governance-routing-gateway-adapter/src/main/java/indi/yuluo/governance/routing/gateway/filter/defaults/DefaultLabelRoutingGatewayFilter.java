package indi.yuluo.governance.routing.gateway.filter.defaults;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.cloud.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.gateway.constants.LabelRoutingGatewayConstants;
import indi.yuluo.governance.routing.gateway.context.LabelRoutingGatewayContext;
import indi.yuluo.governance.routing.gateway.filter.LabelRoutingGatewayFilter;
import indi.yuluo.governance.routing.gateway.util.LabelRoutingGatewayFilterResolver;
import com.alibaba.cloud.routing.properties.LabelRoutingProperties;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class DefaultLabelRoutingGatewayFilter implements LabelRoutingGatewayFilter {

	// Filter order.
	@Value("${" + LabelRoutingGatewayConstants.GATEWAY_ROUTE_FILTER_ORDER + ":"
			+ LabelRoutingGatewayConstants.GATEWAY_ROUTE_FILTER_ORDER_VALUE + "}")
	protected Integer filterOrderNum;

	// Gateway rule priority switch.
	@Value("${" + LabelRoutingGatewayConstants.GATEWAY_HEADER_PRIORITY + ":true}")
	protected Boolean gatewayRequestHeaderPriority;

	@Resource
	private LabelRoutingProperties properties;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		LabelRoutingGatewayContext.getCurrentContext().setExchange(exchange);

		ServerHttpRequest request = exchange.getRequest();
		ServerHttpRequest.Builder requestBuilder = request.mutate();

		applyRequestHeader(request, requestBuilder);

		ServerHttpRequest newRequest = requestBuilder.build();
		ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();

		LabelRoutingGatewayContext.getCurrentContext().setExchange(newExchange);

		return chain.filter(newExchange);
	}

	private void applyRequestHeader(ServerHttpRequest request,
			ServerHttpRequest.Builder requestBuilder) {

		// Use map to simplify if... else statement
		Map<String, String> propertiesMap = new HashMap<>();
		propertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_ZONE,
				properties.getZone());
		LabelRoutingGatewayContext.getCurrentContext().setZone(properties.getZone());
		propertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_REGION,
				properties.getRegion());
		LabelRoutingGatewayContext.getCurrentContext().setRegion(properties.getRegion());
		LabelRoutingGatewayContext.getCurrentContext().setServerHttpRequest(request);

		propertiesMap.forEach(
				(k, v) -> LabelRoutingGatewayFilterResolver.setRequestHeader(request,
						requestBuilder, k, v, gatewayRequestHeaderPriority));

	}

	@Override
	public int getOrder() {

		return filterOrderNum;
	}

}
