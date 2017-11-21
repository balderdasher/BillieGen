package com.billiegen.common.config;

import com.billiegen.common.fmk.RichFreeMarkerView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
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
@ComponentScan(value = "com.mrdios.back",
        includeFilters = @ComponentScan.Filter(value = Controller.class))
public class BackConfig extends BaseConfig {
    private static final Logger logger = LogManager.getLogger();

    public BackConfig() {
        logger.info("ServletBack context is initializing.");
    }

    @Bean
    @Primary
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setViewClass(RichFreeMarkerView.class);
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setPrefix("/" + propertyResolver.getProperty("billie.back.theme") + "/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * 后台视图处理器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

    /**
     * 后台国际化信息
     *
     * @return
     */
    @Bean
    @Primary
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(5);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasenames("languages/back/messages");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        cookieLocaleResolver.setCookieMaxAge(-1);
        cookieLocaleResolver.setCookieName("BACK_LANGUAGE");
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
