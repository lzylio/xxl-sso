集成客户端,共4步
    (参考 xxl-sso-web-sample-springboot)
一、maven依赖
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-sso-core</artifactId>
    <version>1.1.1-SNAPSHOT</version>
</dependency>
二、配置 XxlSsoFilter
参考代码：xxl-sso-web-sample-springboot→com.xxl.sso.sample.config.XxlSsoConfig
三、配置说明(application.properties)
参考代码：xxl-sso-web-sample-springboot→application.properties
四、配置集群
需要用域名来做集群的名字
