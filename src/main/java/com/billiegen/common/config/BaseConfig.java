package com.billiegen.common.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置类基类,实现EnvironmentAware接口方便获取配置信息
 *
 * @author CodePorter
 * @date 2017-10-27
 */
public class BaseConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {
    RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment);
    }
}
