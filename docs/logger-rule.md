# 日志规范

日志我们需要达到二个目的

1. 找到对应的机器
2. 找到用户做了什么

## 找到对应的机器

集群环境我们需要在中间服务器的Nginx/Apache配置，让**前台**就可以知道代码是在哪个节点执行的。

以Nginx举例，如下配置（主要是**upstream\_addr**）

```
upstream code_server{
    server 127.0.0.1:8080;
    server 127.0.0.1:18080;
    ip_hash;
    keepalive 32;
}

server{
    listen 80;
    server_name xwjie.com;

    location /plm {
        proxy_pass http://code_server/plm;
        add_header x-slave $upstream_addr;
    }

}
```

## 找到用户做了什么

使用log4j的**MDC**\(Mapped Diagnostic Context\)类，然后修改日志格式。需要在入口Filter的时候把**用户信息**放进去，这样每一条日志就带了用户信息。

**Filter中填充用户信息：**

**日志格式配置：**

```
<layout class="org.apache.log4j.PatternLayout">
  <param name="ConversionPattern" value="[%t]%-d{MM-dd HH:mm:ss,SSS} %-5p: %X{user} - %c - %m%n" />/>
</layout>
```

**效果图：**![](/pictures/nginx.png)

## 日志打印点

> **不会有人看日志，除非发生了问题。**

日志不能太多，原则是能不加就不加，而不是能加就加。建议的日志打印点有：

### 1. 分支语句的变量需要打印变量

分支语句变量会影响代码走向，不打日志无法知道究竟走那个分支。如下

```java
// optype决定代码走向，需要打印日志
logger.info("edit user, opType: {}", opType);

if (opType == CREATE) {
  // 新增操作
} else if (opType == UPDATE) {
  // 修改操作
} else {
  // 错误的类型，抛出异常
  throw new IllegalArgumentException("unknown optype: {}", opType);
}
```

如果没有打印optype的值，出了问题你只能从前找到后，看optype是什么了，很浪费时间。

> 重要建议：养成增加else语句，把不合法参数抛出异常的好习惯。
>
> 抛异常的时候把对应的非法值抛出来，减少定位时间。（否则你要花时间定位是没有传还是传错了）

### 2. 修改（新增/删除）操作需要打印操作的对象

这点是为了跟踪。防止出现，一个数据被删除了，找不到谁做的。如

```java
private void deleteDoc(long id) {

  logger.info("delete doc, id: {}" , id);

  // 删除代码
}
```

### 3. 大量数据操作的时候需要打印数据长度

建议前后打印日志，而且要打印出数据长度，目的是为了知道 处理了多少数据用了多少时间 。

```java
logger.info("query docment start, params: {}" , params);
List<Document> docList = query(params);
logger.info("query docment done, size: {}" , docList.size())
```

## 日志最终效果图

![](/pictures/log1.png)

