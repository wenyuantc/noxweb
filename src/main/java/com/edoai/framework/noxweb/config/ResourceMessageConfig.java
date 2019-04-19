package com.edoai.framework.noxweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Nox (HuangWenYuan)
 * @description: 国际化消息配置
 * @create: 2019-04-19 14:55
 **/
@Configuration
public class ResourceMessageConfig {

    /**
     * 国际化资源
     * @return
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 设置国际化资源编码
        messageSource.setDefaultEncoding("UTF-8");
        // 设置国际化资源名称,如果放在目录里面要设置  目录名称/文件名称
        messageSource.setBasenames("i18n/message");
        return messageSource;
    }
}
