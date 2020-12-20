package cn.xiaowenjie.codetemplate.common.aop;

import cn.xiaowenjie.codetemplate.common.beans.ResultBean;
import javafx.application.Application;
import javafx.stage.Stage;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static javafx.application.Application.launch;

/**
 * @Description Controller AOP 处理异常并打印调用日志。
 * @Date 2020/12/20
 * @Author 晓风轻 https://github.com/xwjie
 */
@Aspect
@Component
public class ResultBeanControllerAOP extends ControllerAOP {

    @Override
    @Pointcut("execution(public cn.xiaowenjie.codetemplate.common.beans.ResultBean *(..)))")
    public void targetMethod() {
    }

    @Override
    protected IErrorMsg createResult() {
        return new ResultBean<>();
    }
}
