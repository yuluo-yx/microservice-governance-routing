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

package indi.yuluo.governance.routing.context;

import javax.servlet.http.HttpServletRequest;

import indi.yuluo.governance.routing.constant.LabelRoutingConstants;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public abstract class AbstractLabelRoutingContextHolder
		implements LabelRoutingContextHolder {

	@Override
	public String getLabelRouteRegion() {

		return LabelRoutingConstants.DEFAULT;
	}

	@Override
	public String getLabelRouteZone() {

		return LabelRoutingConstants.DEFAULT;
	}

	@Override
	public HttpServletRequest getgHttpServletRequest() {

		return null;
	}

	@Override
	public ServerHttpRequest getServerHttpRequest() {

		return null;
	}

}