package cn.xiaowenjie.codetemplate.common.utils;

import java.util.Collection;

/**
 * 工具类的规范例子
 *
 * @author 晓风轻 https://xwjie.github.io/PLMCodeTemplate/
 */
public class StringUtil {

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
