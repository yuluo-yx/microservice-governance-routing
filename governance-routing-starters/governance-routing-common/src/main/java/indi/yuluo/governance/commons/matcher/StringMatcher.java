package indi.yuluo.governance.commons.matcher;

import java.util.Locale;
import java.util.regex.Pattern;

import indi.yuluo.governance.commons.lang.StringUtils;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class StringMatcher implements Matcher {

	private String matcher;

	private StringMatcherType type;

	private boolean isIgnoreCase;

	private String regex;

	public StringMatcher() {

	}

	public StringMatcher(String regex) {
		this.regex = regex;
		this.type = StringMatcherType.REGEX;
	}

	public StringMatcher(String matcher, StringMatcherType type, boolean isIgnoreCase) {
		this.matcher = matcher;
		this.type = type;
		this.isIgnoreCase = isIgnoreCase;
	}

	public boolean match(Object obj) {
		if (!(obj instanceof String)) {
			return false;
		}
		String str = (String) obj;
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		if (isIgnoreCase) {
			str = str.toLowerCase(Locale.ROOT);
			matcher = matcher.toLowerCase(Locale.ROOT);
		}
		switch (type) {
		case EXACT:
			return str.equals(matcher);
		case PREFIX:
			return str.startsWith(matcher);
		case SUFFIX:
			return str.endsWith(matcher);
		case CONTAIN:
			return str.contains(matcher);
		case REGEX:
			try {
				return Pattern.matches(regex, str);
			}
			catch (Exception e) {
				return false;
			}
		default:
			throw new UnsupportedOperationException(
					"unsupported string compare operation");
		}
	}

	public String getMatcher() {
		return matcher;
	}

	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	public StringMatcherType getType() {
		return type;
	}

	public void setType(StringMatcherType type) {
		this.type = type;
	}

	public boolean isIgnoreCase() {
		return isIgnoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		isIgnoreCase = ignoreCase;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

}
