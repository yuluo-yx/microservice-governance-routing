package indi.yuluo.governance.routing.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public interface LabelRoutingContextHolder {

	String getLabelRouteRegion();

	String getLabelRouteZone();

	ServerHttpRequest getServerHttpRequest();

	HttpServletRequest getgHttpServletRequest();

}
