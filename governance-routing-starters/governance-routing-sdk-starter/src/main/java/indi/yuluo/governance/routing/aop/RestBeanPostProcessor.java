package indi.yuluo.governance.routing.aop;

import indi.yuluo.governance.routing.aop.interceptor.RestTemplateInterceptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class RestBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private RestTemplateInterceptor restTemplateInterceptor;

	@Override
	public Object postProcessBeforeInitialization(Object targetBean, String beanName)
			throws BeansException {

		return targetBean;
	}

	@Override
	public Object postProcessAfterInitialization(Object targetBean, String beanName)
			throws BeansException {

		if (targetBean instanceof RestTemplate) {
			RestTemplate restTemplate = (RestTemplate) targetBean;
			restTemplate.getInterceptors().add(restTemplateInterceptor);
		}

		return targetBean;
	}

}
