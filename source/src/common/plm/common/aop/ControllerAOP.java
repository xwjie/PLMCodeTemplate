package plm.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import plm.common.beans.ResultBean;
import plm.common.exceptions.CheckException;
import plm.common.exceptions.UnloginException;

/**
 * 处理和包装异常
 * 
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
public class ControllerAOP {
	private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

	public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();

		ResultBean<?> result;
		
		try {
			result = (ResultBean<?>) pjp.proceed();
			Object[] args = pjp.getArgs();
		
			logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
		} catch (Throwable e) {
			result = handlerException(pjp, e);
		}

		return result;
	}

	private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResultBean<?> result = new ResultBean();

		// 已知异常
		if (e instanceof CheckException) {
			result.setMsg(e.getLocalizedMessage());
			result.setCode(ResultBean.CHECK_FAIL);
		} else if (e instanceof UnloginException) {
			result.setMsg("Unlogin");
			result.setCode(ResultBean.NO_LOGIN);
		} else {
			logger.error(pjp.getSignature() + " error ", e);

			//TODO 未知的异常，应该格外注意，可以发送邮件通知等
			result.setMsg(e.toString());
			result.setCode(ResultBean.UNKNOWN_EXCEPTION);
		}

		return result;
	}
}
