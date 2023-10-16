package indi.yuluo.governance.routing.zuul.util;

import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public final class LabelRoutingZuulFilterResolver {

	private LabelRoutingZuulFilterResolver() {
	}

	public static void setHeader(RequestContext context, String headerName,
			String headerValue, Boolean zuulHeaderPriority) {
		if (StringUtils.isEmpty(headerValue)) {
			return;
		}

		if (zuulHeaderPriority) {

			context.addZuulRequestHeader(headerName, headerValue);
		}
		else {

			String header = context.getRequest().getHeader(headerName);

			if (StringUtils.isEmpty(header)) {

				context.addZuulRequestHeader(headerName, headerValue);
			}

		}
	}

}
