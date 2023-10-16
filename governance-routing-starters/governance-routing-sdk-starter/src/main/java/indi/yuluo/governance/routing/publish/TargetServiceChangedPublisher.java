package indi.yuluo.governance.routing.publish;

import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.cloud.commons.governance.event.TargetServiceChangedEvent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class TargetServiceChangedPublisher implements ApplicationContextAware {

	private ConcurrentHashMap<String, Object> targetServiceMap = new ConcurrentHashMap<String, Object>();

	private final Object object = new Object();

	public void addTargetService(String targetService) {
		Object value = targetServiceMap.putIfAbsent(targetService, object);
		if (value == null && applicationContext != null) {
			applicationContext.publishEvent(new TargetServiceChangedEvent(targetService));
		}
	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
