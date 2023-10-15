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

package indi.yuluo.governance.routing.gateway.configuration;

import com.alibaba.cloud.routing.context.LabelRoutingContextHolder;
import indi.yuluo.governance.routing.gateway.context.defaults.DefaultLabelRoutingGatewayContextHolder;
import indi.yuluo.governance.routing.gateway.filter.LabelRoutingGatewayClearFilter;
import indi.yuluo.governance.routing.gateway.filter.LabelRoutingGatewayFilter;
import indi.yuluo.governance.routing.gateway.filter.defaults.DefaultLabelRoutingGatewayClearFilter;
import indi.yuluo.governance.routing.gateway.filter.defaults.DefaultLabelRoutingGatewayFilter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@Configuration
@AutoConfigureBefore(RibbonClientConfiguration.class)
public class LabelRoutingGatewayConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public LabelRoutingGatewayFilter labelRoutingGatewayFilter() {

		return new DefaultLabelRoutingGatewayFilter();
	}

	@Bean
	@ConditionalOnMissingBean
	public LabelRoutingGatewayClearFilter labelRoutingGatewayClearFilter() {

		return new DefaultLabelRoutingGatewayClearFilter();
	}

	@Bean
	@ConditionalOnMissingBean
	public LabelRoutingContextHolder labelRoutingContextHolder() {

		return new DefaultLabelRoutingGatewayContextHolder();
	}

}