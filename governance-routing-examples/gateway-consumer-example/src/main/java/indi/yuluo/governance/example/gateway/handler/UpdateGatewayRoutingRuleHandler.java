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

package indi.yuluo.governance.example.gateway.handler;

import indi.yuluo.governance.example.gateway.service.UpdateGatewayRoutingRuleService;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

@Service
public class UpdateGatewayRoutingRuleHandler implements HandlerFunction<ServerResponse> {

	@Autowired
	private UpdateGatewayRoutingRuleService routingRuleService;

	@Override
	public Mono<ServerResponse> handle(ServerRequest serverRequest) {

		routingRuleService.updateDataFromControlPlaneTest();

		return ServerResponse.status(HttpStatus.OK)
				.body(BodyInserters.fromValue("Spring Cloud Gateway Updating routing rule success！"));

	}

}
