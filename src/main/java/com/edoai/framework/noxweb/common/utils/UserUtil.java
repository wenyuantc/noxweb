package com.edoai.framework.noxweb.common.utils;

import java.util.Locale;

/**
 * @author Nox (HuangWenYuan)
 * @description: 用户工具类
 * @create: 2019-04-19 14:42
 **/
public class UserUtil {
    private final static ThreadLocal<String> tlUser = new ThreadLocal<String>();

    private final static ThreadLocal<Locale> tlLocale = ThreadLocal.withInitial(() -> {
        // 语言的默认值
        return Locale.CHINESE;
    });

    public static final String KEY_LANG = "lang";

    public static final String KEY_USER = "user";

    public static void setUser(String userid) {
        tlUser.set(userid);

//        // 把用户信息放到log4j
//        MDC.put(KEY_USER, userid);
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

//        MDC.remove(KEY_USER);
    }
}
