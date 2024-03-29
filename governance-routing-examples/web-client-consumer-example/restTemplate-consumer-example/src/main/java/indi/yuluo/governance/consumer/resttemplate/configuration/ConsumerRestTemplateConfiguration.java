/*
 * Copyright 2013-2023 the original author or authors.
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

package indi.yuluo.governance.consumer.resttemplate.configuration;

import indi.yuluo.governance.consumer.resttemplate.interceptor.ConsumerRestRequestInterceptor;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 *
 * Resolve an issue where the response stream can only be read once.
 */

@Configuration
public class ConsumerRestTemplateConfiguration {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		BufferingClientHttpRequestFactory simpleBufferingClientHttpRequest = new BufferingClientHttpRequestFactory(
				requestFactory);

		RestTemplate restTemplateClient = new RestTemplate(
				simpleBufferingClientHttpRequest);
		restTemplateClient.getInterceptors().add(new ConsumerRestRequestInterceptor());

		return restTemplateClient;
	}

}
