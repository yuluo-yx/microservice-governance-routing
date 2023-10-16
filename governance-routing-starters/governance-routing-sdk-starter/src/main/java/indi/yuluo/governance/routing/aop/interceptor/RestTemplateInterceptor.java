package indi.yuluo.governance.routing.aop.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import indi.yuluo.governance.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.context.LabelRoutingContext;
import indi.yuluo.governance.routing.properties.LabelRoutingProperties;
import com.alibaba.nacos.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	/**
	 * Whether the RestTemplate core policy header is passed. When the global subscription
	 * is started, you can disable the core policy header delivery, which can save the
	 * size of the transmitted data and improve performance to certain extent
	 */
	@Value("${" + LabelRoutingConstants.REST_HEADER_TRANSMISSION_ENABLED + ":true}")
	protected Boolean restTemplateCoreHeaderTransmissionEnabled;

	@Resource
	private LabelRoutingProperties properties;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {

		applyHeader(request);

		return execution.execute(request, body);
	}

	private void applyHeader(HttpRequest request) {

		Map<String, String> routingPropertiesMap = new HashMap<>();
		HttpHeaders headers = request.getHeaders();

		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_ZONE,
				properties.getZone());
		LabelRoutingContext.getCurrentContext().setRoutingZone(properties.getZone());
		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_REGION,
				properties.getRegion());
		LabelRoutingContext.getCurrentContext().setRoutingRegion(properties.getRegion());

		routingPropertiesMap.forEach((k, v) -> {
			if (StringUtils.isNotEmpty(k)
					&& !StringUtils.equals(k, LabelRoutingConstants.DEFAULT)) {

				headers.add(k, v);
			}
		});

	}

}
