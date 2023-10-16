package indi.yuluo.governance.routing.zuul.configuration;

import indi.yuluo.governance.routing.context.LabelRoutingContextHolder;
import indi.yuluo.governance.routing.zuul.context.defaults.DefaultLabelRoutingZuulContextHolder;
import indi.yuluo.governance.routing.zuul.filter.LabelRoutingZuulFilter;
import indi.yuluo.governance.routing.zuul.filter.defaults.DefaultLabelRoutingZuulFilter;

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
public class LabelRoutingZuulConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public LabelRoutingContextHolder routingZuulContextHolder() {

		return new DefaultLabelRoutingZuulContextHolder();
	}

	@Bean
	@ConditionalOnMissingBean
	public LabelRoutingZuulFilter routingZuulFilter() {

		return new DefaultLabelRoutingZuulFilter();
	}

}
