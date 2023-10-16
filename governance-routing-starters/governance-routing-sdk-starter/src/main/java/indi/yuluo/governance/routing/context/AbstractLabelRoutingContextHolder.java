package indi.yuluo.governance.routing.context;

import javax.servlet.http.HttpServletRequest;

import indi.yuluo.governance.routing.constant.LabelRoutingConstants;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public abstract class AbstractLabelRoutingContextHolder
		implements LabelRoutingContextHolder {

	@Override
	public String getLabelRouteRegion() {

		return LabelRoutingConstants.DEFAULT;
	}

	@Override
	public String getLabelRouteZone() {

		return LabelRoutingConstants.DEFAULT;
	}

	@Override
	public HttpServletRequest getgHttpServletRequest() {

		return null;
	}

	@Override
	public ServerHttpRequest getServerHttpRequest() {

		return null;
	}

}
