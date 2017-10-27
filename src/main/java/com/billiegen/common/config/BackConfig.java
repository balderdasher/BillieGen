package com.billiegen.common.config;

import com.billiegen.common.fmk.RichFreeMarkerView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
@ComponentScan(basePackages = "com.example.back",
        includeFilters = @ComponentScan.Filter(value = Controller.class))
public class BackConfig extends BaseConfig {
    private static final Logger logger = LogManager.getLogger();

    public BackConfig() {
        logger.info("ServletBack is initializing.");
    }

    /**
     * 后台视图处理器
     *
     * @param viewResolver
     * @return
     */
    @Bean(name = "backViewResolver")
    public CommandLineRunner backViewResolver(FreeMarkerViewResolver viewResolver) {
        return args -> {
            viewResolver.setViewClass(RichFreeMarkerView.class);
            viewResolver.setPrefix("/theme/" + propertyResolver.getProperty("billie.back.theme") + "/");
            viewResolver.setSuffix(".html");
        };
    }

    /**
     * 后台国际化信息
     *
     * @return
     */
    @Bean(name = "backMessageSource")
    public ReloadableResourceBundleMessageSource backMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setBasename("languages/back/messages");
        return messageSource;
    }

    @Bean(name = "backLocaleResolver")
    public CookieLocaleResolver backLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINESE);
        cookieLocaleResolver.setCookieMaxAge(-1);
        cookieLocaleResolver.setCookieName("BACK_LANGUAGE");
        return cookieLocaleResolver;
    }

    @Bean(name = "backLocaleChangeInterceptor")
    public LocaleChangeInterceptor backLocaleChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(backLocaleChangeInterceptor());
    }

    /**
     * 后台servlet
     *
     * @return
     */
    @Bean(name = "servletBack")
    public ServletRegistrationBean servletBack(ApplicationContext applicationContext) {
        DispatcherServlet servletBack = new DispatcherServlet();
        servletBack.setApplicationContext(applicationContext);
        String adminRootPath = propertyResolver.getProperty("billie.back.path");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servletBack, adminRootPath);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setName("billieServletBack");
        return servletRegistrationBean;
    }
}
