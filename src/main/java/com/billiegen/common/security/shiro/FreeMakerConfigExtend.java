package com.billiegen.common.security.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author CodePorter
 * @date 2017-10-23
 */
@Component
public class FreeMakerConfigExtend {
    private final Configuration configuration;

    @Autowired
    public FreeMakerConfigExtend(Configuration configuration) {
        this.configuration = configuration;
    }

    @PostConstruct
    public void setShareVariable() {
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
