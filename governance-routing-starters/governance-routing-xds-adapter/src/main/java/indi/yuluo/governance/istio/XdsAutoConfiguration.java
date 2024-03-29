package indi.yuluo.governance.istio;

import java.util.List;

import indi.yuluo.governance.commons.governance.event.GovernanceEvent;
import indi.yuluo.governance.istio.filter.XdsResolveFilter;
import indi.yuluo.governance.istio.filter.impl.AuthXdsResolveFilter;
import indi.yuluo.governance.istio.filter.impl.RoutingXdsResolveFilter;
import indi.yuluo.governance.istio.protocol.impl.CdsProtocol;
import indi.yuluo.governance.istio.protocol.impl.EdsProtocol;
import indi.yuluo.governance.istio.protocol.impl.LdsProtocol;
import indi.yuluo.governance.istio.protocol.impl.RdsProtocol;
import io.envoyproxy.envoy.config.listener.v3.Listener;
import io.envoyproxy.envoy.config.route.v3.RouteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.cloud.istio.config.enabled", matchIfMissing = true)
@EnableConfigurationProperties(XdsConfigProperties.class)
// We need to auto config the class after all the governance data listener, to prevent
// event publisher hang permanently.
@AutoConfigureOrder(XdsAutoConfiguration.RESOURCE_TRANSFORM_AUTO_CONFIG_ORDER)
public class XdsAutoConfiguration {

	/**
	 * xds auto configuration log.
	 */
	private static final Logger log = LoggerFactory.getLogger(XdsAutoConfiguration.class);

	/**
	 * Order of xds auto config.
	 */
	public static final int RESOURCE_TRANSFORM_AUTO_CONFIG_ORDER = 100;

	@Autowired
	private XdsConfigProperties xdsConfigProperties;

	@Bean
	public DummyGovernanceDataListener dummyGovernanceDataListener() {
		return new DummyGovernanceDataListener();
	}

	@Bean
	public XdsChannel xdsChannel() {
		return new XdsChannel(xdsConfigProperties);
	}

	@Bean
	public XdsScheduledThreadPool xdsScheduledThreadPool() {
		return new XdsScheduledThreadPool(xdsConfigProperties);
	}

	@Bean
	public XdsResolveFilter<List<Listener>> authXdsResolveFilter() {
		return new AuthXdsResolveFilter();
	}

	@Bean
	public XdsResolveFilter<List<RouteConfiguration>> routingXdsResolveFilter() {
		return new RoutingXdsResolveFilter();
	}

	@Bean
	public PilotExchanger pilotExchanger(LdsProtocol ldsProtocol, CdsProtocol cdsProtocol,
			EdsProtocol edsProtocol, RdsProtocol rdsProtocol) {
		return new PilotExchanger(ldsProtocol, cdsProtocol, edsProtocol, rdsProtocol);
	}

	@Bean
	public LdsProtocol ldsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool,
			List<XdsResolveFilter<List<Listener>>> filters) {
		return new LdsProtocol(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties,
				filters);
	}

	@Bean
	public CdsProtocol cdsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool) {
		return new CdsProtocol(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties);
	}

	@Bean
	public EdsProtocol edsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool) {
		return new EdsProtocol(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties);
	}

	@Bean
	public RdsProtocol rdsProtocol(XdsChannel xdsChannel,
			XdsScheduledThreadPool xdsScheduledThreadPool,
			List<XdsResolveFilter<List<RouteConfiguration>>> filters) {
		return new RdsProtocol(xdsChannel, xdsScheduledThreadPool, xdsConfigProperties,
				filters);
	}

	/**
	 * To prevent the event publish hang permanently.
	 */
	private final class DummyGovernanceDataListener
			implements ApplicationListener<GovernanceEvent> {

		@Override
		public void onApplicationEvent(GovernanceEvent event) {
			if (log.isDebugEnabled()) {
				log.debug("Received governance event " + event.toString());
			}
		}

	}

}
