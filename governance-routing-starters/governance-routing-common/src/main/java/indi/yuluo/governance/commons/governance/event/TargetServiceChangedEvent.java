package indi.yuluo.governance.commons.governance.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class TargetServiceChangedEvent extends ApplicationEvent {

	public TargetServiceChangedEvent(Object source) {
		super(source);
	}

}
