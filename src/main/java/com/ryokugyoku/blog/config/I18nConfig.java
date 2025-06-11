package com.ryokugyoku.blog.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


import java.util.Locale;

@Configuration
public class I18nConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    /** デフォルトはブラウザの言語にする */
    @Bean
    public LocaleResolver localeResolver() {
        return new HeaderAwareSessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");     // 例: /post/1?lang=en
        return interceptor;
    }

    /** メッセージ・バンドルを読み込む */
    @Bean
    public MessageSource messageSource() {
        var ms = new ResourceBundleMessageSource();
        ms.setBasename("messages");      // messages_xx.properties
        ms.setDefaultEncoding("UTF-8");
        ms.setFallbackToSystemLocale(false);
        return ms;
    }


}
