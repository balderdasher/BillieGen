package com.billiegen.common.config;

import com.billiegen.common.fmk.RichFreeMarkerView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
@ComponentScan(basePackages = "com.example.front",
        includeFilters = @ComponentScan.Filter(value = Controller.class)
)
public class FrontConfig extends BaseConfig {
    private static final Logger logger = LogManager.getLogger();

    public FrontConfig() {
        logger.info("ServletFront is initializing.");
    }

//    /**
//     * 前台视图处理器
//     *
//     * @param viewResolver
//     * @return
//     */
//    @Bean(name = "frontViewResolver")
//    public CommandLineRunner frontViewResolver(FreeMarkerViewResolver viewResolver) {
//        return args -> {
//            viewResolver.setViewClass(RichFreeMarkerView.class);
//            viewResolver.setPrefix("/front_1/");
//            viewResolver.setSuffix(".html");
//        };
//    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setViewClass(RichFreeMarkerView.class);
        viewResolver.setPrefix("/front_1/");
        viewResolver.setSuffix(".html");
        registry.viewResolver(viewResolver);
    }

    /**
     * 前台国际化信息
     *
     * @return
     */
    @Bean(name = "frontMessageSource")
    public ReloadableResourceBundleMessageSource frontMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setBasename("languages/front/messages");
        return messageSource;
    }

    @Bean(name = "frontLocaleResolver")
    public CookieLocaleResolver frontLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINESE);
        cookieLocaleResolver.setCookieMaxAge(-1);
        cookieLocaleResolver.setCookieName("FRONT_LANGUAGE");
        return cookieLocaleResolver;
    }

    @Bean(name = "frontLocaleChangeInterceptor")
    public LocaleChangeInterceptor frontLocaleChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(frontLocaleChangeInterceptor());
    }

    /**
     * 前台servlet
     *
     * @return
     */
    @Bean(name = "servletFront")
    public ServletRegistrationBean servletBack() {
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.example.front");
        DispatcherServlet servletBack = new DispatcherServlet();
        servletBack.setApplicationContext(applicationContext);
        String frontRootPath = propertyResolver.getProperty("billie.front.path");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servletBack, frontRootPath);
        servletRegistrationBean.setLoadOnStartup(2);
        servletRegistrationBean.setName("billieServletFront");
        return servletRegistrationBean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:G:/mrdios/projects/billiegen/resource/metronic/");
        super.addResourceHandlers(registry);
    }
}
