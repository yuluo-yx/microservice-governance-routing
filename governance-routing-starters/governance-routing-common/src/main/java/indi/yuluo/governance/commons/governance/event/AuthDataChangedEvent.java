package indi.yuluo.governance.commons.governance.event;

import indi.yuluo.governance.commons.governance.auth.rule.AuthRules;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class AuthDataChangedEvent extends GovernanceEvent {

	/**
	 * Configuration for authentication.
	 */
	private final AuthRules authRules;

	public AuthDataChangedEvent(Object source, AuthRules authRules) {
		super(source);
		this.authRules = authRules;
	}

	public AuthRules getAuthRules() {
		return authRules;
	}

}
