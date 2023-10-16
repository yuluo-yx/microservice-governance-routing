package indi.yuluo.governance.routing.zuul.context;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class LabelRoutingZuulContext {

	private static final ThreadLocal<LabelRoutingZuulContext> THREAD_LOCAL = ThreadLocal
			.withInitial(LabelRoutingZuulContext::new);

	public static LabelRoutingZuulContext getCurrentContext() {

		return THREAD_LOCAL.get();
	}

	public static void clearCurrentContext() {

		THREAD_LOCAL.remove();
	}

	private String region;

	private String zone;

	private HttpServletRequest httpServletRequest;

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

	public HttpServletRequest getHttpServletRequest() {

		return httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {

		this.httpServletRequest = httpServletRequest;
	}

}
