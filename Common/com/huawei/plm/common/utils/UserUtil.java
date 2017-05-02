package com.huawei.plm.common.utils;

import java.util.Locale;

import org.apache.log4j.MDC;

public class UserUtil {

	private final static ThreadLocal<String> tlUser = new ThreadLocal<>();

	private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
		protected Locale initialValue() {
			// 语言的默认值
			return Locale.CHINA;
		};
	};

	public static final String KEY_LANG = "lang";

	public static final String KEY_USER = "user";

	public static void setUser(String userid) {
		tlUser.set(userid);
		
		// log4j
		MDC.put(KEY_USER, userid);
	}

	public static String getUser() {
		return tlUser.get();
	}

	public static void setLocale(String locale) {
		setLocale(new Locale(locale));
	}

	public static void setLocale(Locale locale) {
		tlLocale.set(locale);
	}

	public static Locale getLocale() {
		return tlLocale.get();
	}

	public static void clearAllUserInfo() {
		tlUser.remove();
		tlLocale.remove();
		
		MDC.remove(KEY_USER);
	}
}
