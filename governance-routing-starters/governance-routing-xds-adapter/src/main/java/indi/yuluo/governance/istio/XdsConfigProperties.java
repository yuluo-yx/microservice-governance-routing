package indi.yuluo.governance.istio;

import javax.annotation.PostConstruct;

import indi.yuluo.governance.commons.lang.StringUtils;
import indi.yuluo.governance.istio.constant.IstioConstants;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@ConfigurationProperties(XdsConfigProperties.PREFIX)
public class XdsConfigProperties {

	/**
	 * Prefix in yaml.
	 */
	public static final String PREFIX = "spring.cloud.istio.config";

	private String host;

	private int port;

	private int pollingPoolSize;

	private int pollingTime;

	/**
	 * jwt token for istiod 15012 port.
	 */
	private String istiodToken;

	private Boolean logXds;

	@PostConstruct
	public void init() {
		if (this.port <= 0 || this.port > 65535) {
			this.port = IstioConstants.ISTIOD_SECURE_PORT;
		}
		if (StringUtils.isEmpty(host)) {
			this.host = IstioConstants.DEFAULT_ISTIOD_ADDR;
		}
		if (pollingPoolSize <= 0) {
			pollingPoolSize = IstioConstants.DEFAULT_POLLING_SIZE;
		}
		if (pollingTime <= 0) {
			pollingTime = IstioConstants.DEFAULT_POLLING_TIME;
		}
		if (logXds == null) {
			logXds = true;
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPollingPoolSize() {
		return pollingPoolSize;
	}

	public void setPollingPoolSize(int pollingPoolSize) {
		this.pollingPoolSize = pollingPoolSize;
	}

	public int getPollingTime() {
		return pollingTime;
	}

	public void setPollingTime(int pollingTime) {
		this.pollingTime = pollingTime;
	}

	public String getIstiodToken() {
		return istiodToken;
	}

	public void setIstiodToken(String istiodToken) {
		this.istiodToken = istiodToken;
	}

	public boolean isLogXds() {
		return Boolean.TRUE.equals(logXds);
	}

	public void setLogXds(boolean logXds) {
		this.logXds = logXds;
	}

}
