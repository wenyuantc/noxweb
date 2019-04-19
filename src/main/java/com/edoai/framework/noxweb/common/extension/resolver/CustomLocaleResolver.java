package com.edoai.framework.noxweb.common.extension.resolver;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author Nox (HuangWenYuan)
 * @description: 自定义国际化处理
 * @create: 2019-04-19 15:16
 **/
public class CustomLocaleResolver implements LocaleResolver {

    private final static String I18N_HEADER = "language";

    @Override
    public Locale resolveLocale(HttpServletRequest req) {
        // 默认美国英文
        Locale locale = Locale.US;
        String language = req.getParameter(I18N_HEADER);
        if (StrUtil.isBlank(language)) {
            language = req.getHeader(I18N_HEADER);
        }

        if (StrUtil.isNotBlank(language)) {
            String[] lang = language.split("_");
            if (lang.length == 1) {
                locale = new Locale(lang[0]);
            } else {
                locale = new Locale(lang[0], lang[1]);
            }
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
