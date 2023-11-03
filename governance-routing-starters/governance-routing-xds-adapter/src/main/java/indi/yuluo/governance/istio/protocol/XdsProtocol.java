package indi.yuluo.governance.istio.protocol;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public interface XdsProtocol<T> {

	List<T> getResource(Set<String> resourceNames);

	String getTypeUrl();

	long observeResource(Set<String> resourceNames, Consumer<List<T>> consumer);

}
