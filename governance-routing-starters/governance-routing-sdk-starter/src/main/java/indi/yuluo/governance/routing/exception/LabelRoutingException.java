package indi.yuluo.governance.routing.exception;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class LabelRoutingException extends RuntimeException {

	private static final long serialVersionUID = 7975167663357170658L;

	public LabelRoutingException() {
		super();
	}

	public LabelRoutingException(String message) {
		super(message);
	}

	public LabelRoutingException(String message, Throwable cause) {
		super(message, cause);
	}

	public LabelRoutingException(Throwable cause) {
		super(cause);
	}

}
