package indi.yuluo.governance.routing.gateway.context;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class LabelRoutingGatewayContext {

	private static final ThreadLocal<LabelRoutingGatewayContext> THREAD_LOCAL = ThreadLocal
			.withInitial(LabelRoutingGatewayContext::new);

	public static LabelRoutingGatewayContext getCurrentContext() {

		return THREAD_LOCAL.get();
	}

	public static void clearCurrentContext() {

		THREAD_LOCAL.remove();
	}

	private ServerWebExchange exchange;

	public ServerWebExchange getExchange() {
		return exchange;
	}

	public void setExchange(ServerWebExchange exchange) {
		this.exchange = exchange;
	}

	private ServerHttpRequest serverHttpRequest;

	private String region;

	private String zone;

	public ServerHttpRequest getServerHttpRequest() {
		return serverHttpRequest;
	}

	public void setServerHttpRequest(ServerHttpRequest serverHttpRequest) {
		this.serverHttpRequest = serverHttpRequest;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
