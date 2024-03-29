package indi.yuluo.governance.routing.aop.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.cloud.commons.lang.StringUtils;
import indi.yuluo.governance.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.context.LabelRoutingContext;
import indi.yuluo.governance.routing.properties.LabelRoutingProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class OpenFeignInterceptor implements RequestInterceptor {

	/**
	 * Whether the core policy header is passed on Feign. When the global subscription is
	 * started, you can disable the core policy header delivery, which can save the size
	 * of the transmitted data and improve performance to certain extent.
	 */
	@Value("${" + LabelRoutingConstants.FEIGN_HEADER_TRANSMISSION_ENABLED + ":true}")
	protected Boolean feignCoreHeaderTransmissionEnabled;

	@Resource
	private LabelRoutingProperties properties;

	@Override
	public void apply(RequestTemplate request) {

		applyRequestHeader(request);

	}

	private void applyRequestHeader(RequestTemplate request) {

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

				request.header(k, v);
			}
		});

	}

}
