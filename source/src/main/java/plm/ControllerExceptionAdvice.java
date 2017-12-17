package plm;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import plm.common.beans.ResultBean;
import plm.common.exceptions.CheckException;

/**
 * !!不要使用这种方式代替AOP
 *
 */
@ControllerAdvice
public class ControllerExceptionAdvice {

	@ExceptionHandler(CheckException.class)
	@ResponseBody
	public ResultBean processCheckException(NativeWebRequest request, CheckException e) {
		ResultBean result = new ResultBean();

		result.setCode(ResultBean.CHECK_FAIL);
		result.setMsg(e.getMessage());

		return result;
	}
}