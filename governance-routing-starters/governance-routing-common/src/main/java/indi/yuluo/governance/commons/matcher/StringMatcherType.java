package indi.yuluo.governance.commons.matcher;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public enum StringMatcherType {

	/**
	 * exact match.
	 */
	EXACT("exact"),
	/**
	 * prefix match.
	 */
	PREFIX("prefix"),
	/**
	 * suffix match.
	 */
	SUFFIX("suffix"),
	/**
	 * present match.
	 */
	PRESENT("present"),
	/**
	 * regex match.
	 */
	REGEX("regex"),
	/**
	 * contain match.
	 */
	CONTAIN("contain");

	/**
	 * type of matcher.
	 */
	public final String type;

	StringMatcherType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}

}
