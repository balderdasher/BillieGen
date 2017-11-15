package com.mrdios.billiegen.common.config;

import com.mrdios.billiegen.common.fmk.RichFreeMarkerView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
 * equals to servlet-front.xml config
 * 前台servlet配置,只扫描前台Controller
 *
 * @author CodePorter
 * @date 2017-10-27
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mrdios.example.front",
        includeFilters = @ComponentScan.Filter(value = Controller.class)
)
public class FrontConfig extends BaseConfig {
    private static final Logger logger = LogManager.getLogger();

    public FrontConfig() {
        logger.info("ServletFront context is initializing.");
    }

    @Bean
    @Primary
    public ViewResolver mvcViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setViewClass(RichFreeMarkerView.class);
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setPrefix("/front_1/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * 前台视图处理器
     *
     * @return
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(mvcViewResolver());
    }

//    /**
//     * 前台国际化信息
//     *
//     * @return
//     */
//    @Bean
//    public ReloadableResourceBundleMessageSource frontMessageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setCacheSeconds(-1);
//        messageSource.setBasename("languages/front/messages");
//        return messageSource;
//    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINESE);
        cookieLocaleResolver.setCookieMaxAge(-1);
        cookieLocaleResolver.setCookieName("FRONT_LANGUAGE");
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
