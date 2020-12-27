
# MyBatis配置

只需要一个配置

```java
@MapperScan("cn.xiaowenjie.codetemplate.daos")
```

使用插件生成对应的Mapping XML文件即可。

# druid配置

> spring.profiles.include=db

只需要配置`application-db.properties`，然后返回`DruidDataSource`即可。

```java
@Bean
@ConfigurationProperties(prefix = "spring.datasource")
public DataSource druid() {
    return new DruidDataSource();
}
```

## druid监控页面

http://localhost:8080/druid/

admin/123456

# swagger配置

不需要任何代码配置，只需要引入maven依赖。

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

url: 

http://localhost:8080/swagger-ui/index.html

## UI改进

```xml
<!--整合Knife4j-->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>2.0.4</version>
</dependency>
```

url:

http://localhost:8080/doc.html#/