package com;

/**
 * @author CodePorter
 * @date 2017-09-29
 */
//@RestController
//@SpringBootApplication
//@ServletComponentScan
//@ComponentScan(basePackages = "com.billiegen",
//        excludeFilters = {
//                @ComponentScan.Filter(value = Controller.class),
//                @ComponentScan.Filter(value = RestController.class)
//        }
//)
public class Application {

//    public Application() {
//        super();
//        setRegisterErrorPageFilter(false);
//    }
//
//    @RequestMapping("hi")
//    public String hello() {
//        return "Hello World!";
//    }
//
//    @Bean
//    public FreeMarkerViewResolver viewResolver(FreeMarkerViewResolver viewResolver) {
//        viewResolver.setViewClass(RichFreeMarkerView.class);
//        return viewResolver;
//    }
//
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
//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(Locale.CHINESE);
//        cookieLocaleResolver.setCookieMaxAge(-1);
//        cookieLocaleResolver.setCookieName("LANGUAGE");
//        return cookieLocaleResolver;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor backLocaleChangeInterceptor() {
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("lang");
//        return interceptor;
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(Application.class);
//        application.setBannerMode(Banner.Mode.LOG);
//        application.run(args);
//    }
}
