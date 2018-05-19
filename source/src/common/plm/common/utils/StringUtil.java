package plm.common.utils;

		import java.util.Collection;

/**
 * 工具类的规范例子
 *
 * @author  晓风轻 https://xwjie.github.io/PLMCodeTemplate/
 *
 */
public class StringUtil {

	public static boolean isEmpty(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isEmpty(cs);
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}
}
