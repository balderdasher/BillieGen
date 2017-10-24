package com.billiegen.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * 配置filter、listener等
 *
 * @author CodePorter
 * @date 2017-10-24
 */
@Configuration
public class WebConfig {

    /**
     * open session in view filter
     *
     * @return OpenEntityManagerInViewFilter
     */
    @Bean
    public FilterRegistrationBean openSessionInViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new OpenEntityManagerInViewFilter());
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }
}
