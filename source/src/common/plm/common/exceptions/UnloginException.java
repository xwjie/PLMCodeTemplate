package plm.common.exceptions;

/**
 * 没有登陆异常
 * 
 * @author 肖文杰 https://xwjie.github.io/PLMCodeTemplate/
 *
 */
public class UnloginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnloginException() {
	}

	public UnloginException(String message) {
		super(message);
	}

	public UnloginException(Throwable cause) {
		super(cause);
	}

	public UnloginException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnloginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
