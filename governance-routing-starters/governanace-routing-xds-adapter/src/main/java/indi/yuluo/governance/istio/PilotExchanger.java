package indi.yuluo.governance.istio;

import java.util.List;
import java.util.Set;

import indi.yuluo.governance.istio.protocol.impl.CdsProtocol;
import indi.yuluo.governance.istio.protocol.impl.EdsProtocol;
import indi.yuluo.governance.istio.protocol.impl.LdsProtocol;
import indi.yuluo.governance.istio.protocol.impl.RdsProtocol;
import io.envoyproxy.envoy.config.cluster.v3.Cluster;
import io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment;
import io.envoyproxy.envoy.config.listener.v3.Listener;
import io.envoyproxy.envoy.config.route.v3.RouteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.CollectionUtils;

/**
 * PilotExchanger is the class which communicate with istio pilot.
 *
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class PilotExchanger {

	private static final Logger log = LoggerFactory.getLogger(PilotExchanger.class);

	private final LdsProtocol ldsProtocol;

	private final CdsProtocol cdsProtocol;

	private final EdsProtocol edsProtocol;

	private final RdsProtocol rdsProtocol;

	private void observeListeners(List<Listener> listeners) {
		if (CollectionUtils.isEmpty(listeners)) {
			return;
		}
		Set<String> resourceName = ldsProtocol.getResourceNames();
		if (!CollectionUtils.isEmpty(resourceName)) {
			rdsProtocol.observeResource(resourceName, this::observeRoutes);
		}

	}

	private void observeClusters(List<Cluster> clusters) {
		Set<String> resourceName = cdsProtocol.getResourceNames();
		if (!CollectionUtils.isEmpty(resourceName)) {
			// eds
			edsProtocol.observeResource(resourceName, this::observeEndpoints);
		}
		else {
			// lds
			ldsProtocol.observeResource(null, this::observeListeners);
		}

	}

	private void observeEndpoints(List<ClusterLoadAssignment> endpoints) {
		ldsProtocol.observeResource(null, this::observeListeners);

	}

	private void observeRoutes(List<RouteConfiguration> routes) {
		if (log.isDebugEnabled()) {
			log.debug("A Xds configuration update is finished");
		}
	}

	public PilotExchanger(LdsProtocol ldsProtocol, CdsProtocol cdsProtocol,
			EdsProtocol edsProtocol, RdsProtocol rdsProtocol) {
		this.ldsProtocol = ldsProtocol;
		this.cdsProtocol = cdsProtocol;
		this.edsProtocol = edsProtocol;
		this.rdsProtocol = rdsProtocol;
		// observe cluster first, and update the other xds sequentially
		this.ldsProtocol.setNeedPolling(false);
		this.edsProtocol.setNeedPolling(false);
		this.rdsProtocol.setNeedPolling(false);
		// only polling cds, other protocol will be obtained sequentially
		this.cdsProtocol.setNeedPolling(true);
		cdsProtocol.observeResource(null, this::observeClusters);
	}

}
