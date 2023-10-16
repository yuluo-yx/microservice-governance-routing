package indi.yuluo.governance.commons.matcher;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY,
		property = "className")
public interface Matcher {

	boolean match(Object obj);

}
