## Oauth2认证服务搭建
- 在Pom文件中添加依赖,主要有SpringSecurity、Oauth2、 redis、 jwt
- 在application.yml、bootstrap.yml 添加 Nacos、Redis 相关配置、主要是服务注册、与redis连接相关配置
- 使用KeyTool 生成 jwt.jks 证书, 并复制到resources目录下
```
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
```
- 创建UserServiceImpl类实现UserDetailsService,用于加载用户信息
 
