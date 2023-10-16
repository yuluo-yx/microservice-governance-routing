package indi.yuluo.governance.commons.governance.auth.rule;

import java.util.Map;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class AuthRules {

	private final Map<String, AuthRule> allowAuthRules;

	private final Map<String, AuthRule> denyAuthRules;

	private final Map<String, JwtRule> jwtRules;

	public AuthRules(Map<String, AuthRule> allowAuthRules,
			Map<String, AuthRule> denyAuthRules, Map<String, JwtRule> jwtRules) {
		this.allowAuthRules = allowAuthRules;
		this.denyAuthRules = denyAuthRules;
		this.jwtRules = jwtRules;
	}

	public Map<String, AuthRule> getAllowAuthRules() {
		return allowAuthRules;
	}

	public Map<String, AuthRule> getDenyAuthRules() {
		return denyAuthRules;
	}

	public Map<String, JwtRule> getJwtRules() {
		return jwtRules;
	}

}
