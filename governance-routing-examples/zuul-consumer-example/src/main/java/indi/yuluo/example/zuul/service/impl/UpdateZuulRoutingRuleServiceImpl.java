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

package indi.yuluo.example.zuul.service.impl;

import java.io.IOException;
import java.util.List;

import com.alibaba.cloud.commons.governance.event.RoutingDataChangedEvent;
import com.alibaba.cloud.commons.governance.routing.UnifiedRoutingDataStructure;
import indi.yuluo.example.zuul.service.UpdateZuulRoutingRuleService;
import indi.yuluo.governance.routing.common.GovernanceRoutingConstants;
import indi.yuluo.governance.routing.converter.Converter;
import indi.yuluo.governance.routing.util.ReadJsonFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

@Service
public class UpdateZuulRoutingRuleServiceImpl
		implements UpdateZuulRoutingRuleService, ApplicationContextAware {

	private static final Logger log = LoggerFactory
			.getLogger(UpdateZuulRoutingRuleServiceImpl.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		this.applicationContext = applicationContext;
	}

	@Autowired
	private Converter<String, List<UnifiedRoutingDataStructure>> jsonConverter;

	private static String updateRoutingRulePath;

	static {
		org.springframework.core.io.Resource resource = new ClassPathResource(
				"update-routing-rule.json");

		try {
			updateRoutingRulePath = resource.getFile().getPath();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateDataFromControlPlaneTest() {

		log.info("Access /updateRule interface，update routing rule success..." + "\n"
				+ GovernanceRoutingConstants.UPDATE_RULE_DESCRIPTION);

		String content = ReadJsonFileUtils.convertFile2String(updateRoutingRulePath);
		List<UnifiedRoutingDataStructure> unifiedRouteDataStructureList = jsonConverter
				.convert(content);

		applicationContext.publishEvent(
				new RoutingDataChangedEvent(this, unifiedRouteDataStructureList));

		log.info("Update routing rule success!");

	}

}
