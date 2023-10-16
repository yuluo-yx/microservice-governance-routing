package indi.yuluo.governance.commons.matcher;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class PortMatcher implements Matcher {

	private Integer matcher;

	public PortMatcher() {

	}

	public PortMatcher(Integer matcher) {
		this.matcher = matcher;
	}

	@Override
	public boolean match(Object object) {
		if (!(object instanceof Integer)) {
			return false;
		}
		return matcher != null && matcher.equals(object);
	}

	public void setMatcher(Integer matcher) {
		this.matcher = matcher;
	}

}
