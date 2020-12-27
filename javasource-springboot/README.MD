
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

