
# MyBatis配置

只需要一个配置

```java
@MapperScan("cn.xiaowenjie.codetemplate.daos")
```

使用插件生成对应的Mapping XML文件即可。

## 自动设置创建者id

```java
@Component
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Slf4j
public class MyBatisUpdateInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 根据签名指定的args顺序获取具体的实现类
        // 1. 获取MappedStatement实例, 并获取当前SQL命令类型
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType commandType = ms.getSqlCommandType();

        // 2. 获取当前正在被操作的类, 有可能是Java Bean, 也可能是普通的操作对象, 比如普通的参数传递
        // 普通参数, 即是 @Param 包装或者原始 Map 对象, 普通参数会被 Mybatis 包装成 Map 对象
        // 即是 org.apache.ibatis.binding.MapperMethod$ParamMap
        Object parameter = invocation.getArgs()[1];
        // 获取拦截器指定的方法类型, 通常需要拦截 update
        String methodName = invocation.getMethod().getName();

        // methodName; update, commandType: INSERT
        log.info("NormalPlugin, methodName; {}, commandType: {}", methodName, commandType);

        // 3. 获取当前用户信息
        // UserEntity userEntity = UserUtil.getUser();

        // 插入数据
        if (commandType.equals(SqlCommandType.INSERT)) {
            if (parameter instanceof BaseEntity) {
                // 4. 实体类
                BaseEntity entity = (BaseEntity) parameter;
                entity.setCreator(999);
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
```

## 插入对象返回新id

```java
dao.insert(config);

// 插入对象后，得到新对象id
long newId = config.getId();
```

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

引入下面这个组件，UI易用性更加好。

```xml
<!--整合Knife4j-->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>2.0.8</version>
</dependency>
```

url:

http://localhost:8080/doc.html#/

比较好用的特性有接口文档里面忽略某些属性：`@ApiOperationSupport`

```java
@ApiOperation("添加配置")
@ApiOperationSupport(ignoreParameters = {"config.id", "config.createTime", "config.updateTime"})
@PostMapping("/add")
@Log(action = LogConst.ACTION_ADD, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#config.name")
public ResultBean<Long> add(@RequestBody Config config) {
    return new ResultBean<>(configService.add(config));
}
```

参考： [给Swagger换了个新皮肤，瞬间高大上了！ - 简书](https://www.jianshu.com/p/02cea650699a)

## 生产环境关闭swagger

```java
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enable:true}")
    private boolean enableSwagger;

    @Bean
    public Docket customImplementation() {
        return new Docket(SWAGGER_2).enable(enableSwagger);
    }
}
```

[Swagger 2与OpenAPI 3 - 简书](https://www.jianshu.com/p/879baf1cff07)

