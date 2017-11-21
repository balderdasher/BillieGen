## 在yml配置文件中，不能用占位符做map的key，如
```yml
userBaseUrl: /user
accessAllow: true
accessAllowMap:
  /img/**: ${accessAllow}
  /css/**: ${accessAllow}
  ${userBaseUrl}/edit: false
```
  
以上配置的结果是：img和css的配置可以正常解析，而最后一个配置将不解析

## springboot中bean的名字跟配置bean的方法名有关
如下代码所示：

```
@Bean
public LocaleResolver localeResolver() {
    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
    cookieLocaleResolver.setCookieMaxAge(-1);
    cookieLocaleResolver.setCookieName("BACK_LANGUAGE");
    return cookieLocaleResolver;
}
```

当改变方法名时，诡异的事情发生了，国际化功能不可用了，拼接lang=en_US等的链接提示404。

同样地，在继承WebMvcConfigurerAdapter自定义mvc配置时，如自定义freemarker视图处理器设置模板前缀视图后缀名等时，只有方法名为viewResolver时，自定义的freemarker视图处理器才能够被正确识别，换做别的方法名如mvcResolver等时将发现配置没有起作用

## springboot中使用多DispatcherServlet时的静态资源处理

在springboot中配置多个DispatcherServlet时，比如前后台分别用不同的DispatcherServlet，此时的静态资源路径和DispatcherServlet的urlMapping配置有一定关系：

* 当url匹配规则为'/'或者'*.xx'时，静态资源访问正常，页面链接如：/images/logo.png
* 当url匹配规则为'/xx'，并且框架默认的DispatcherServlet被覆盖或者使用`@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)`屏蔽时，静态资源路径必须要加上这个规则前缀才能够正常访问，页面链接如：/xx/images/logo.png

所以，为了使用上的方便，默认的DispatcherServlet不要屏蔽(或许也有在屏蔽掉之后的解决办法),同时在各DispatcherServlet绑定的配置类中重写`configureDefaultServletHandling`方法开启DefaultServletHandler以便把静态资源请求绑定到默认的DispatcherServlet上

# springboot中spring data jpa实体扫描规则

在springboot中使用jpa时，如果相应配置类上没有用注解`@EntityScan(value = "com.**.entity")`指定实体扫描路径，那么此时扫描的范围将由springboot的启动类`Application`决定，扫描此类所在包下以及子包下所有带`@Entity`注解的实体类，所以，如果没有用注解指定实体扫描的范围，应该将主启动类直于能扫描到所有实体的位置，否则程序启动时会报`Not a managed type: class ******`的异常。所以spring官方建议将主启动类放在根包之下。