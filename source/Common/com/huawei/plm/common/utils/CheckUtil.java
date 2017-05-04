package com.huawei.plm.common.utils;

import org.springframework.context.MessageSource;

import com.huawei.plm.common.exceptions.CheckException;

public class CheckUtil {
	private static final Object[] NullArgs = new Object[0];

	private static MessageSource resources;

	public static void setResources(MessageSource resources) {
		CheckUtil.resources = resources;
	}

	public static void check(boolean condition, String msgKey) {
		if (!condition) {
			fail(msgKey);
		}
	}

	public static void notEmpty(String str, String msgKey) {
		if (str == null || str.isEmpty()) {
			fail(msgKey);
		}
	}

	public static void notNull(Object obj, String msgKey) {
		if (obj == null) {
			fail(msgKey);
		}
	}

	private static void fail(String msgKey) {
		throw new CheckException(resources.getMessage(msgKey, NullArgs, UserUtil.getLocale()));
	}
}
