package indi.yuluo.governance.commons.governance.routing.rule;

import java.util.Objects;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class UrlRoutingRule {

	public static class PathRoutingRule implements Rule {

		private final String type = "path";

		private String condition;

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
			return null;
		}

		@Override
		public void setKey(String key) {
			//
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
			PathRoutingRule path = (PathRoutingRule) o;
			return Objects.equals(getType(), path.getType())
					&& Objects.equals(getCondition(), path.getCondition())
					&& Objects.equals(getValue(), path.getValue());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getType(), getCondition(), getValue());
		}

		@Override
		public String toString() {
			return "PathRoutingRule{" + "type='" + type + '\'' + ", condition='"
					+ condition + '\'' + ", value='" + value + '\'' + '}';
		}

	}

	public static class ParameterRoutingRule implements Rule {

		private final String type = "parameter";

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
			ParameterRoutingRule parameterRoutingRule = (ParameterRoutingRule) o;
			return Objects.equals(getType(), parameterRoutingRule.getType())
					&& Objects.equals(getCondition(), parameterRoutingRule.getCondition())
					&& Objects.equals(getKey(), parameterRoutingRule.getKey())
					&& Objects.equals(getValue(), parameterRoutingRule.getValue());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getType(), getCondition(), getKey(), getValue());
		}

		@Override
		public String toString() {
			return "ParameterRoutingRule{" + "type='" + type + '\'' + ", condition='"
					+ condition + '\'' + ", key='" + key + '\'' + ", value='" + value
					+ '\'' + '}';
		}

	}

}
