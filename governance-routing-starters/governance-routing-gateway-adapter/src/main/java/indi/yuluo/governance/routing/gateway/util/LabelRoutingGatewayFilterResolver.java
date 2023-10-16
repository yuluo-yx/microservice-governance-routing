package indi.yuluo.governance.routing.gateway.util;

import com.alibaba.cloud.commons.lang.StringUtils;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public final class LabelRoutingGatewayFilterResolver {

	private LabelRoutingGatewayFilterResolver() {
	}

	public static void setRequestHeader(ServerHttpRequest request,
			ServerHttpRequest.Builder requestBuilder, String requestHeaderName,
			String requestHeaderValue, Boolean gatewayRequestHeaderPriority) {

		if (StringUtils.isEmpty(requestHeaderValue)) {
			return;
		}

		if (gatewayRequestHeaderPriority) {

			// Under the gateway priority condition, clear all external headers.
			requestBuilder.headers(headers -> headers.remove(requestHeaderName));

			// Add the header set by the gateway.
			requestBuilder.headers(
					headers -> headers.add(requestHeaderName, requestHeaderValue));
		}
		else {
			/**
			 * If the gateway is not prioritized, determine whether the external request
			 * contains headers. If it does, the built-in header is not added.
			 */
			if (!request.getHeaders().containsKey(requestHeaderName)) {
				requestBuilder.headers(
						headers -> headers.add(requestHeaderName, requestHeaderValue));
			}
		}

	}

}
