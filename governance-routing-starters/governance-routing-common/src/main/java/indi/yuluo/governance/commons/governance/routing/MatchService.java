package indi.yuluo.governance.commons.governance.routing;

import java.util.List;
import java.util.Objects;

import indi.yuluo.governance.commons.governance.routing.rule.Rule;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class MatchService {

	private List<Rule> ruleList;

	private String version;

	private Integer weight;

	private String fallbackVersion;

	public String getFallback() {
		return fallbackVersion;
	}

	public void setFallback(String fallbackVersion) {
		this.fallbackVersion = fallbackVersion;
	}

	public List<Rule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<Rule> ruleList) {
		this.ruleList = ruleList;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MatchService that = (MatchService) o;
		return getWeight().equals(that.getWeight())
				&& Objects.equals(getRuleList(), that.getRuleList())
				&& Objects.equals(getFallback(), that.getFallback())
				&& Objects.equals(getVersion(), that.getVersion());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRuleList(), getVersion(), getWeight(), getFallback());
	}

	@Override
	public String toString() {
		return "MatchService{" + "ruleList=" + ruleList + ", version='" + version + '\''
				+ ", weight=" + weight + ", getFallback=" + fallbackVersion + '}';
	}

}
