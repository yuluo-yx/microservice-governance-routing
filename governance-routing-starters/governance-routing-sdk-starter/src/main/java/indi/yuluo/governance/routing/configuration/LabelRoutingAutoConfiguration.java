/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indi.yuluo.governance.routing.configuration;

import indi.yuluo.governance.routing.aop.ReactiveBeanPostProcessor;
import indi.yuluo.governance.routing.aop.RestBeanPostProcessor;
import indi.yuluo.governance.routing.aop.interceptor.OpenFeignInterceptor;
import indi.yuluo.governance.routing.aop.interceptor.ReactiveInterceptor;
import indi.yuluo.governance.routing.aop.interceptor.RestTemplateInterceptor;
import indi.yuluo.governance.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.context.LabelRoutingContextHolder;
import indi.yuluo.governance.routing.context.defaults.DefaultLabelLabelRoutingContextHolder;
import indi.yuluo.governance.routing.listener.RoutingDataListener;
import indi.yuluo.governance.routing.publish.TargetServiceChangedPublisher;
import indi.yuluo.governance.routing.repository.FilterService;
import indi.yuluo.governance.routing.repository.RoutingDataRepository;
import indi.yuluo.governance.routing.ribbon.RoutingLoadBalanceRule;
import feign.Feign;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(LabelRoutingAutoConfiguration.ROUTING_AUTO_CONFIG_ORDER)
public class LabelRoutingAutoConfiguration {

	/**
	 * Order of label routing auto config.
	 */
	public static final int ROUTING_AUTO_CONFIG_ORDER = 10;

	@Bean
	@ConditionalOnMissingBean
	public RoutingDataRepository routingDataRepository() {

		return new RoutingDataRepository();
	}

	@Bean
	@ConditionalOnMissingBean
	public FilterService filterService() {

		return new FilterService();
	}

	@Bean
	public RoutingDataListener routingDataListener(
			RoutingDataRepository routingDataRepository, FilterService filterService) {

		return new RoutingDataListener(routingDataRepository, filterService);
	}

	@Bean
	public TargetServiceChangedPublisher targetServiceChangedPublisher() {

		return new TargetServiceChangedPublisher();
	}

	@Bean
	@ConditionalOnMissingBean
	public OpenFeignInterceptor routingFeignInterceptor() {

		return new OpenFeignInterceptor();
	}

	@Bean
	@ConditionalOnMissingBean
	public RoutingLoadBalanceRule routingLoadBalanceRule() {

		return new RoutingLoadBalanceRule();
	}

	@Bean
	@ConditionalOnMissingBean
	public LabelRoutingContextHolder routingContextHolder() {

		return new DefaultLabelLabelRoutingContextHolder();
	}

	@ConditionalOnClass(Feign.class)
	protected static class RoutingFeignConfiguration {

		@Bean
		@ConditionalOnProperty(value = LabelRoutingConstants.FEIGN_INTERCEPT_ENABLED,
				matchIfMissing = true)
		public OpenFeignInterceptor feignRequestInterceptor() {
			return new OpenFeignInterceptor();
		}

	}

	@ConditionalOnClass(RestTemplate.class)
	protected static class RoutingRestTemplateConfiguration {

		@Bean
		@ConditionalOnProperty(value = LabelRoutingConstants.REST_INTERCEPT_ENABLED,
				matchIfMissing = true)
		public RestTemplateInterceptor restTemplateInterceptor() {
			return new RestTemplateInterceptor();
		}

		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnProperty(value = LabelRoutingConstants.REST_INTERCEPT_ENABLED,
				matchIfMissing = true)
		public RestBeanPostProcessor restBeanPostProcessor() {
			return new RestBeanPostProcessor();
		}

	}

	@ConditionalOnClass(WebClient.class)
	@ConditionalOnBean(WebClient.Builder.class)
	protected static class RoutingWebClientConfiguration {

		@Bean
		@ConditionalOnProperty(value = LabelRoutingConstants.REACTIVE_INTERCEPT_ENABLED,
				matchIfMissing = true)
		public ReactiveInterceptor reactiveInterceptor() {
			return new ReactiveInterceptor();
		}

		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnProperty(value = LabelRoutingConstants.REACTIVE_INTERCEPT_ENABLED,
				matchIfMissing = true)
		public ReactiveBeanPostProcessor reactiveBeanPostProcessor() {
			return new ReactiveBeanPostProcessor();
		}

	}

}
