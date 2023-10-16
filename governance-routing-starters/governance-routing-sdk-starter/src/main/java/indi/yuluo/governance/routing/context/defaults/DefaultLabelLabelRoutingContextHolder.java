package indi.yuluo.governance.routing.context.defaults;

import javax.servlet.http.HttpServletRequest;

import indi.yuluo.governance.routing.context.AbstractLabelRoutingContextHolder;
import indi.yuluo.governance.routing.context.LabelRoutingContext;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class DefaultLabelLabelRoutingContextHolder
		extends AbstractLabelRoutingContextHolder {

	@Override
	public String getLabelRouteRegion() {

		return LabelRoutingContext.getCurrentContext().getRoutingRegion();
	}

	@Override
	public String getLabelRouteZone() {

		return LabelRoutingContext.getCurrentContext().getRoutingZone();
	}

	@Override
	public HttpServletRequest getgHttpServletRequest() {

		return LabelRoutingContext.getCurrentContext().getServletRequest();
	}

}
