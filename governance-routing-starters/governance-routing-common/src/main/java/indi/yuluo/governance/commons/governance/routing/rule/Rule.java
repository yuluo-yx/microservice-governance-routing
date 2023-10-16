package indi.yuluo.governance.commons.governance.routing.rule;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public interface Rule {

	/**
	 * get type of rule.
	 * @return String
	 */
	default String getType() {
		return null;
	}

	/**
	 * get condition.
	 * @return String
	 */
	default String getCondition() {
		return null;
	}

	/**
	 * set condition.
	 * @param condition {@link String}
	 */
	default void setCondition(String condition) {
	}

	/**
	 * get key of rule.
	 * @return String
	 */
	default String getKey() {
		return null;
	}

	/**
	 * set key of rule.
	 * @param key {@link String}
	 */
	default void setKey(String key) {
	}

	/**
	 * get value of rule.
	 * @return String
	 */
	default String getValue() {
		return null;
	}

	/**
	 * set value of rule.
	 * @param value {@link String}
	 */
	default void setValue(String value) {
	}

}
