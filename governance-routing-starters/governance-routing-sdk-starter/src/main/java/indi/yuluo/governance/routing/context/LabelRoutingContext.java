package indi.yuluo.governance.routing.context;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class LabelRoutingContext {

	private static final ThreadLocal<LabelRoutingContext> THREAD_LOCAL = ThreadLocal
			.withInitial(LabelRoutingContext::new);

	private String routingRegion;

	private String routingZone;

	private HttpServletRequest servletRequest;

	public HttpServletRequest getServletRequest() {

		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public static LabelRoutingContext getCurrentContext() {

		return THREAD_LOCAL.get();
	}

	public static void clearCurrentContext() {

		THREAD_LOCAL.remove();
	}

	public String getRoutingRegion() {
		return routingRegion;
	}

	public void setRoutingRegion(String routingRegion) {
		this.routingRegion = routingRegion;
	}

	public String getRoutingZone() {
		return routingZone;
	}

	public void setRoutingZone(String routingZone) {
		this.routingZone = routingZone;
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
