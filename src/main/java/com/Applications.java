package com;

import com.billiegen.common.config.BackConfig;
import com.billiegen.common.config.FrontConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
@RestController
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = "com.billiegen",
        excludeFilters = {
                @ComponentScan.Filter(value = Controller.class),
                @ComponentScan.Filter(value = RestController.class)
        }
)
public class Applications extends SpringBootServletInitializer {

    public Applications() {
        super();
        setRegisterErrorPageFilter(false);
    }

    @RequestMapping("hi")
    public String hello() {
        return "Hello World!";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Applications.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.LOG)
                .sources(Applications.class)
                .child(BackConfig.class)
                .child(FrontConfig.class)
                .web(true)
                .run(args);
    }
}
