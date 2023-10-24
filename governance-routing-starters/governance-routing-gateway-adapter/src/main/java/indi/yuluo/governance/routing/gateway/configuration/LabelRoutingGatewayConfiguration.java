package indi.yuluo.governance.routing.gateway.configuration;

import indi.yuluo.governance.routing.context.LabelRoutingContextHolder;
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
