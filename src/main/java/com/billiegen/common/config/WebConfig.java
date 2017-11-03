//package com.billiegen.common.config;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 相当于web.xml配置
// *
// * @author CodePorter
// * @date 2017-10-24
// */
//@Configuration
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    private final Logger logger = LogManager.getLogger();
//
//    /**
//     * open session in view filter
//     *
//     * @return OpenEntityManagerInViewFilter
//     */
//    @Bean
//    public FilterRegistrationBean openSessionInViewFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new OpenEntityManagerInViewFilter());
//        registrationBean.addUrlPatterns("/");
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("excludeSuffixs", "js,css,jpg,jpeg,gif,png,bmp,html");
//        initParams.put("singleSession", "true");
//        registrationBean.setInitParameters(initParams);
//        return registrationBean;
//    }
//
//}
