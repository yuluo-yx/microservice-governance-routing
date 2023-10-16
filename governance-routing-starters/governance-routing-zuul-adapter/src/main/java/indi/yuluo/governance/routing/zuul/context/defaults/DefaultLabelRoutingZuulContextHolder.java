package indi.yuluo.governance.routing.zuul.context.defaults;

import javax.servlet.http.HttpServletRequest;

import indi.yuluo.governance.routing.context.AbstractLabelRoutingContextHolder;
import indi.yuluo.governance.routing.zuul.context.LabelRoutingZuulContext;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class DefaultLabelRoutingZuulContextHolder
		extends AbstractLabelRoutingContextHolder {

	@Override
	public String getLabelRouteRegion() {

		return LabelRoutingZuulContext.getCurrentContext().getRegion();
	}

	@Override
	public String getLabelRouteZone() {

		return LabelRoutingZuulContext.getCurrentContext().getZone();
	}

	@Override
	public HttpServletRequest getgHttpServletRequest() {

		return LabelRoutingZuulContext.getCurrentContext().getHttpServletRequest();
	}

}
