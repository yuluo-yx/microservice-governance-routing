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

package indi.yuluo.governance.examples;

import com.alibaba.cloud.nacos.registry.NacosRegistration;
import indi.yuluo.governance.routing.common.GovernanceRoutingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@EnableDiscoveryClient
@SpringBootApplication
public class A2ProviderApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "a2");
		SpringApplication.run(A2ProviderApplication.class, args);
	}

	@Autowired
	NacosRegistration nacosRegistration;

	@RestController
	class A2Controller {

		@GetMapping("/test-a2")
		public String testA2() {

			String host = nacosRegistration.getHost();
			int port = nacosRegistration.getPort();
			String zone = nacosRegistration.getMetadata().get(GovernanceRoutingConstants.ZONE);
			String region = nacosRegistration.getMetadata().get(GovernanceRoutingConstants.REGION);
			String version = nacosRegistration.getMetadata().get(GovernanceRoutingConstants.VERSION);
			return "Route in " + host + ":" + port + ", region: " + region + ", zone: "
					+ zone + ", version: " + version;

		}

	}

}
