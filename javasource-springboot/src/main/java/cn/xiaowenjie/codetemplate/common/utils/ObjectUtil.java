package cn.xiaowenjie.codetemplate.common.utils;


import lombok.SneakyThrows;

/**
 * 工具类的规范例子
 * 
 * 
 * @author 肖文杰
 * 
 */
public class ObjectUtil {

	@SneakyThrows
	public void copyAttribute(Object source, Object dest) {
		org.springframework.beans.BeanUtils.copyProperties(source, dest);
		// org.apache.commons.beanutils.BeanUtils.copyProperties(dest, source);
	}
}
