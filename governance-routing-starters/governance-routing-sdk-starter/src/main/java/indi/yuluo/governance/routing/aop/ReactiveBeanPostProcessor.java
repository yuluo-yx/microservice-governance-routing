package indi.yuluo.governance.routing.aop;

import indi.yuluo.governance.routing.aop.interceptor.ReactiveInterceptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class ReactiveBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private ReactiveInterceptor webClientInterceptor;

	@Override
	public Object postProcessBeforeInitialization(Object targetBean, String beanName)
			throws BeansException {

		return targetBean;
	}

	@Override
	public Object postProcessAfterInitialization(Object targetBean, String beanName)
			throws BeansException {

		if (targetBean instanceof WebClient.Builder) {
			WebClient.Builder webClientBuilder = (WebClient.Builder) targetBean;

			webClientBuilder.filter(webClientInterceptor);
		}

		return targetBean;
	}

}
