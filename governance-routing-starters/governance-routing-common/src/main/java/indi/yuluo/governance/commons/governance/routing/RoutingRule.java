package indi.yuluo.governance.commons.governance.routing;

import java.util.List;
import java.util.Objects;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class RoutingRule {

	private String defaultRoutingVersion;

	private List<MatchService> matchRouteList;

	public String getDefaultRouteVersion() {
		return defaultRoutingVersion;
	}

	public void setDefaultRouteVersion(String defaultRouteVersion) {
		this.defaultRoutingVersion = defaultRouteVersion;
	}

	public List<MatchService> getMatchRouteList() {
		return matchRouteList;
	}

	public void setMatchRouteList(List<MatchService> matchRouteList) {
		this.matchRouteList = matchRouteList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RoutingRule that = (RoutingRule) o;
		return Objects.equals(defaultRoutingVersion, that.defaultRoutingVersion)
				&& Objects.equals(getMatchRouteList(), that.getMatchRouteList());
	}

	@Override
	public int hashCode() {
		return Objects.hash(defaultRoutingVersion, getMatchRouteList());
	}

	@Override
	public String toString() {
		return "LabelRouteData{" + "defaultRouteVersion='" + defaultRoutingVersion + '\''
				+ ", matchRouteList=" + matchRouteList + '}';
	}

}
