package indi.yuluo.governance.istio;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class XdsScheduledThreadPool extends ScheduledThreadPoolExecutor {

	public XdsScheduledThreadPool(XdsConfigProperties xdsConfigProperties) {
		this(xdsConfigProperties.getPollingPoolSize());
	}

	public XdsScheduledThreadPool(int corePoolSize) {
		super(corePoolSize);
	}

}
