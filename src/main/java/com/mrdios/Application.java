package com.mrdios;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
@RestController
@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
@ServletComponentScan
@ComponentScan(basePackages = "com.mrdios",
        excludeFilters = {
                @ComponentScan.Filter(value = Controller.class),
                @ComponentScan.Filter(value = RestController.class),
                @ComponentScan.Filter(value = EnableWebMvc.class)
        }
)
public class Application extends SpringBootServletInitializer {

    public Application() {
        super();
        setRegisterErrorPageFilter(false);
    }

    @RequestMapping("hi")
    public String hello() {
        return "Hello World!";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.LOG)
                .sources(Application.class)
                .web(true)
                .run(args);
    }
}
