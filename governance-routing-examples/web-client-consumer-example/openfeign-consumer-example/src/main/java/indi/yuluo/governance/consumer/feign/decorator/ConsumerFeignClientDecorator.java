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

package indi.yuluo.governance.consumer.feign.decorator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import feign.Client;
import feign.Request;
import feign.Response;
import indi.yuluo.governance.routing.common.GovernanceRoutingConstants;
import indi.yuluo.governance.routing.entity.ConsumerNodeInfo;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 *
 * Rewrite the feign client to get the feign response.
 */

public class ConsumerFeignClientDecorator implements Client {

	private final Client delegateClient;

	public ConsumerFeignClientDecorator(Client delegateClient) {
		this.delegateClient = delegateClient;
	}

	@Override
	public Response execute(Request request, Request.Options options) throws IOException {

		Response response = delegateClient.execute(request, options);

		List<Map<String, List<String>>> list = new ArrayList<>();

		request.headers().forEach((k, v) -> {
			Map<String, List<String>> headerMap = new HashMap<>();
			List<String> headerValue = new ArrayList<>(v);
			headerMap.put(k, headerValue);
			list.add(headerMap);
		});
		ConsumerNodeInfo.set(GovernanceRoutingConstants.FEIGN_APPLICATION_NAME, list);

		return response;
	}

}
