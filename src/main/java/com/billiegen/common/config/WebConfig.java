package com.billiegen.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CodePorter
 * @date 2017-10-24
 */
@Configuration
public class WebConfig extends BaseConfig {

    private final Logger logger = LogManager.getLogger();

    /**
     * open session in view filter
     *
     * @return OpenEntityManagerInViewFilter
     */
    @Bean
    public FilterRegistrationBean openEntityManagerInViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new OpenEntityManagerInViewFilter());
        registrationBean.addUrlPatterns("/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("excludeSuffixs", "js,css,jpg,jpeg,gif,png,bmp,html");
        initParams.put("singleSession", "true");
        initParams.put("entityManagerFactoryBeanName", "entityManagerFactory");
        registrationBean.setInitParameters(initParams);
        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean backServlet() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(BackConfig.class);
        DispatcherServlet backServlet = new DispatcherServlet(applicationContext);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(backServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings(propertyResolver.getProperty("billie.back.path") + "/*");
        registrationBean.setName("backServlet");
        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean frontServlet() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(FrontConfig.class);
        DispatcherServlet frontServlet = new DispatcherServlet(applicationContext);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(frontServlet);
        registrationBean.setLoadOnStartup(2);
        registrationBean.addUrlMappings(propertyResolver.getProperty("billie.front.path") + "/*");
        registrationBean.setName("frontServlet");
        return registrationBean;
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
