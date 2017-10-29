package com.billiegen;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
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
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.LOG);
        application.run(args);
    }
}
