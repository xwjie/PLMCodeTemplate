package plm.common.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import plm.common.annotations.Log;
import plm.common.utils.SPELUtil;
import plm.common.utils.UserUtil;


/**
 *  日志信息处理AOP
 *  把需要的信息从参数中提取出来，转成json字符串放到MDC中使用。
 *
 *  注意：注解不支持重入（就是嵌套的方法里面还有LOG注解），因为我觉得不需要嵌套
 *  如果你项目中有这种使用场景，自己修改一下也非常简单，就是修改前保存起来即可。
 *
 * @author  晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
@Slf4j
public class LogAOP {

    public static final String JSON_KEY = "logjson";

    @SneakyThrows
    public Object handlerLogMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Object result;

        try {
            putLogInfo2MDC(pjp);

            result = pjp.proceed();

            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        } finally {
            clearMDC();
        }

        return result;
    }

    private void clearMDC() {
        MDC.remove(JSON_KEY);
    }

    private void putLogInfo2MDC(ProceedingJoinPoint pjp) {
        // 得到方法上的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);


        SPELUtil spel = new SPELUtil(pjp);

        JSONObject json = new JSONObject();

        // 使用单字母而不是全名，是为了节省日志文件大小。

        // 用户
        json.put("U", UserUtil.getUserIfLogin());

        // 操作
        json.put("A", logAnnotation.action());

        // 对象类型
        json.put("T", logAnnotation.itemType());

        // 对象id，spel表达式
        json.put("I", spel.cacl(logAnnotation.itemId()));

        // 其他参数，spel表达式
        json.put("P", spel.cacl(logAnnotation.param()));

        MDC.put(JSON_KEY, json.toJSONString());
    }
}
