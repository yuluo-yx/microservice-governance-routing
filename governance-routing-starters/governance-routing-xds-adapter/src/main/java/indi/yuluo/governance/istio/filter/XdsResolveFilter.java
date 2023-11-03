package indi.yuluo.governance.istio.filter;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public interface XdsResolveFilter<T> {

	boolean resolve(T t);

	String getTypeUrl();

}
