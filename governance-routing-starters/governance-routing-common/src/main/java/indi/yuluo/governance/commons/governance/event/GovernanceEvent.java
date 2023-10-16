package indi.yuluo.governance.commons.governance.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class GovernanceEvent extends ApplicationEvent {

	/**
	 * Create a new {@code ApplicationEvent}.
	 * @param source the object on which the event initially occurred or with which the
	 * event is associated (never {@code null})
	 */
	public GovernanceEvent(Object source) {
		super(source);
	}

}
