package com.mrdios.billiegen.common.config;

import com.mrdios.billiegen.common.fmk.RichFreeMarkerView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Locale;

/**
 * equals to servlet-back.xml config
 * 后台servlet配置,只扫描后台Controller
 *
 * @author CodePorter
 * @date 2017-10-27
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mrdios.example.back",
        includeFilters = @ComponentScan.Filter(value = Controller.class))
public class BackConfig extends BaseConfig {
    private static final Logger logger = LogManager.getLogger();

    public BackConfig() {
        logger.info("ServletBack context is initializing.");
    }

    /**
     * 后台视图处理器
     *
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(backViewResolver());
    }

    @Bean
    public FreeMarkerViewResolver backViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setViewClass(RichFreeMarkerView.class);
        viewResolver.setPrefix("/" + propertyResolver.getProperty("billie.back.theme") + "/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * 后台国际化信息
     *
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource backMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setBasename("languages/back/messages");
        return messageSource;
    }

    @Bean
    public CookieLocaleResolver backLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINESE);
        cookieLocaleResolver.setCookieMaxAge(-1);
        cookieLocaleResolver.setCookieName("BACK_LANGUAGE");
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor backLocaleChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(backLocaleChangeInterceptor());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
