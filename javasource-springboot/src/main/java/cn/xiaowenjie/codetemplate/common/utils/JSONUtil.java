package cn.xiaowenjie.codetemplate.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Description JSON工具类
 * 为什么fastjson已经有了工具类还要自己封装呢？
 * 是因为需要隔离具体实现。如果替换了json库，其他代码不需要改动。
 * @Date 2020/12/20
 * @Author 晓风轻 https://github.com/xwjie
 */
public class JSONUtil {
    public static String toJsonStr(Object object) {
        return JSON.toJSONString(object);
    }
}
