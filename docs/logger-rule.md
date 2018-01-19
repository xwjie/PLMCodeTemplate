# 日志规范

日志我们需要达到二个目的

1. 找到对应的机器
2. 找到用户做了什么

## 找到对应的机器

集群环境我们需要在中间服务器的Nginx/Apache配置，让前台就可以知道代码是在哪个节点执行的。

以Nginx举例，如下配置

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

# 找到用户做了什么

使用log4j的MDC\(Mapped Diagnostic Context\)类，然后修改日志格式。需要在Filter的时候把用户信息放进去，这样每一条日志就带了用户信息。

**Filter中填充用户信息：**



**日志格式配置：**

```
<layout class="org.apache.log4j.PatternLayout">
  <param name="ConversionPattern" value="[%t]%-d{MM-dd HH:mm:ss,SSS} %-5p: %X{user} - %c - %m%n" />/>
</layout>
```

**效果图：**![](/pictures/nginx.png)

# 日志打印点





# **最终日志效果**

![](/pictures/log1.png)



