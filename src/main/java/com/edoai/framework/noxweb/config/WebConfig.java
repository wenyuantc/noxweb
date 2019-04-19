package com.edoai.framework.noxweb.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.edoai.framework.noxweb.common.extension.resolver.CustomLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

/**
 * @author Nox (HuangWenYuan)
 * @description: Web配置
 * @create: 2019-04-19 10:00
 **/
@Configuration
public class WebConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {


    /**
     * 自定义国际化
     * @return
     */
    @Bean
    LocaleResolver localeResolver () {
        return new CustomLocaleResolver();
    }

    /**
     * 配置Cors跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 配置跨域的域名, * 代表所有域名都可访问
                .allowedOrigins("*")
                // 允许凭据
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3600);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //  将字符串对象去除双引号
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    /**
     * FastJson配置
     * @return
     */
    @Bean
    public HttpMessageConverter fastHttpMessageConverter(){
        //创建转换对象
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //创建配置文件对象
        FastJsonConfig config = new FastJsonConfig();
        // 结果格式化
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 对象字段为null也显示
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        // 禁用相同引用
        config.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        config.setCharset(Charset.forName("UTF-8"));
        converter.setFastJsonConfig(config);
        return converter;
    }


}
