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

当改变方法名时，诡异的事情发生了，国际化功能不可用了，拼接lang=en_US等的链接提示404，但是对于viewResolver,方法名却不影响使用。WEIRD!!