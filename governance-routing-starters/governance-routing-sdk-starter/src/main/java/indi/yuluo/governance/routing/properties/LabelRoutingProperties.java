package indi.yuluo.governance.routing.properties;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import com.alibaba.cloud.commons.lang.StringUtils;
import indi.yuluo.governance.routing.constant.LabelRoutingConstants;
import indi.yuluo.governance.routing.util.LoadBalanceUtil;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

@ConfigurationProperties(prefix = LabelRoutingConstants.PROPERTY_PREFIX)
public class LabelRoutingProperties implements Serializable {

	private static final long serialVersionUID = 7157091468155324299L;

	/**
	 * Load Balance Rule.
	 */
	private String rule;

	@PostConstruct
	public void init() {
		if (StringUtils.isEmpty(rule)) {
			rule = LoadBalanceUtil.ZONE_AVOIDANCE_RULE;
		}
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	/**
	 * Region staining.
	 */
	private String region;

	/**
	 * Zone staining.
	 */
	private String zone;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

}
