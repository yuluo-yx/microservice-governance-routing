package indi.yuluo.governance.routing.configuration;

import indi.yuluo.governance.routing.properties.LabelRoutingProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ LabelRoutingProperties.class })
public class LabelPropertiesAutoConfiguration {

}
