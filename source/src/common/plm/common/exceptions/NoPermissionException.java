package plm.common.exceptions;

/**
 * 无权限异常
 * 
 * @author 肖文杰 https://xwjie.github.io/PLMCodeTemplate/
 *
 */
public class NoPermissionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoPermissionException() {
	}

	public NoPermissionException(String message) {
		super(message);
	}

	public NoPermissionException(Throwable cause) {
		super(cause);
	}

	public NoPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
