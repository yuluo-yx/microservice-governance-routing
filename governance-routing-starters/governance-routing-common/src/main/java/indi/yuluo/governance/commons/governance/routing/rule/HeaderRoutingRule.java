package indi.yuluo.governance.commons.governance.routing.rule;

import java.util.Objects;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class HeaderRoutingRule implements Rule {

	private final String type = "header";

	private String condition;

	private String key;

	private String value;

	@Override
	public String getCondition() {
		return condition;
	}

	@Override
	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		HeaderRoutingRule that = (HeaderRoutingRule) o;
		return Objects.equals(getType(), that.getType())
				&& Objects.equals(getCondition(), that.getCondition())
				&& Objects.equals(getKey(), that.getKey())
				&& Objects.equals(getValue(), that.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getType(), getCondition(), getKey(), getValue());
	}

	@Override
	public String toString() {
		return "HeaderRoutingRule{" + "type='" + type + '\'' + ", condition='" + condition
				+ '\'' + ", key='" + key + '\'' + ", value='" + value + '\'' + '}';
	}

}
