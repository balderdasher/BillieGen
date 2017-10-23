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