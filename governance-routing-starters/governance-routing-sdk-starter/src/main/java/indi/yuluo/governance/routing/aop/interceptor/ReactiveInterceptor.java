package indi.yuluo.governance.routing.aop.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import indi.yuluo.governance.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.context.LabelRoutingContext;
import indi.yuluo.governance.routing.properties.LabelRoutingProperties;
import com.alibaba.nacos.common.utils.StringUtils;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class ReactiveInterceptor implements ExchangeFilterFunction {

	/**
	 * Whether the Web Client core policy header is delivered. When the global
	 * subscription is started, you can disable the core policy header delivery, which can
	 * save the size of the transmitted data and improve performance to a certain extent
	 */
	@Value("${" + LabelRoutingConstants.WEB_CLIENT_HEADER_TRANSMISSION_ENABLED + ":true}")
	protected Boolean webClientHeaderTransmissionEnabled;

	@Resource
	private LabelRoutingProperties properties;

	@Override
	public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {

		ClientRequest.Builder requestBuilder = ClientRequest.from(request);

		applyRequestHeader(requestBuilder);

		ClientRequest newRequest = requestBuilder.build();

		return next.exchange(newRequest);
	}

	private void applyRequestHeader(ClientRequest.Builder requestBuilder) {

		Map<String, String> routingPropertiesMap = new HashMap<>();
		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_ZONE,
				properties.getZone());
		LabelRoutingContext.getCurrentContext().setRoutingZone(properties.getZone());
		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_REGION,
				properties.getRegion());
		LabelRoutingContext.getCurrentContext().setRoutingRegion(properties.getRegion());

		routingPropertiesMap.forEach((k, v) -> {
			if (StringUtils.isNotEmpty(k)
					&& !StringUtils.equals(k, LabelRoutingConstants.DEFAULT)) {

				requestBuilder.header(k, v);
			}
		});
	}

}
