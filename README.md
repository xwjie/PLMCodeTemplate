# PLMCodeTemplate
给部门制定的代码框架模板。追求工匠精神，编写简单代码。

**[`SpringBoot版本在这里`](https://github.com/xwjie/ElementVueSpringbootCodeTemplate)，持续更新中，后续加入vue+element的代码模板，欢迎加星加watch。**

# 前言

参考 ** [`程序员你为什么这么累`](https://zhuanlan.zhihu.com/p/28705206) 系列文章 **，里面有详细的讲解，评论里面有不同观点的讨论，建议也看看，相信对你有帮助。

![](/pictures/main.png) 

# 目录

> * 优雅编码 - 接口定义规范
> * 优雅编码 - ResultBean的重要性和约束
> * 优雅编码 - 异常处理
> * 优雅编码 - 参数校验和国际化规范
> * 优雅编码 - 日志打印
> * 技术点总结
> * 对开发组长的要求
> * 对开发人员的建议

# 优雅编码 - 接口定义规范

请阅读 [我的编码习惯 - 接口定义](https://zhuanlan.zhihu.com/p/28708259) ，不要犯里面的错误。

1. 所有接口都必须返回ResultBean，就是说一开始就考虑好成功和失败的场景。
2. ResultBean只允许出现在controller，不允许到处传。
3. 不要出现map，json等这种复杂对象做为输入和输出。
4. 对外接口可以考虑使用细分错误码，对内接口使用异常信息即可（方便编码）。


# 优雅编码 - ResultBean的重要性和约束

请阅读 [我的编码习惯 - Controller规范](https://zhuanlan.zhihu.com/p/28717374)。

这里都是对开发组长的要求。


# 优雅编码 - 异常处理

请阅读 [我的编码习惯 - 异常处理](https://zhuanlan.zhihu.com/p/29005176)。

开发人员不准捕获异常，直接抛出。

其他对开发组长的要求请见上面的文章和代码。

# 优雅编码 - 日志打印

请阅读 [我的编程习惯 - 日志建议](https://zhuanlan.zhihu.com/p/28629319)。

日志打印我们部门中普通有2个极端，首先是什么都不打印，出了问题之后一看后台啥也没有，然后修改，什么地方都打印，一出错误，找日志找半天，定位一个问题要1,2个小时。前几年出现过几次前台出错没有日志，测试环境无法重现，把日志加上走个变更再操作再看日志定位的事情，当然现在这种情况比较少了。

所以打印日志有个度，太多或者太少都不好，好钢用在刀刃上。我这里提几点建议。

## 分支语句的变量需要打印变量

分支语句应该变量会影响代码走向，不打日志无法指定究竟走那个分支。如下

```Java
// optype决定代码走向，需要打印日志
logger.info("edit user, opType:" + opType);

if (opType == CREATE) {
  // 新增操作
} else if (opType == UPDATE) {
  // 修改操作
} else {
  // 错误的类型，抛出异常
  throw new IllegalArgumentException("unknown optype:" + opType);
}
```

如果没有打印optype的值，出了问题你只能从前找到后，看optype是什么了，很浪费时间。

> 重要建议：养成把不合法参数抛出异常的好习惯，抛异常的时候把对应的非法值抛出来。

## 修改操作需要打印操作的对象

这点是为了跟踪。防止出现，一个数据被删除了，找不到谁做的。如

```Java
private void deleteDoc(long id) {

  logger.info("delete doc, id:" + id);

  // 删除代码
}
```

## 大量数据操作的时候需要打印数据长度

建议前后打印日志，而且要打印出数据长度，目的是为了知道 `处理了多少数据用了多少时间` 。如一个操作用了3秒钟，性能是好还是坏？ 如果处理了1条数据，那么可能就是性能差，如果处理了10000条数据，那么可能就是性能好。

```Java
logger.info("query docment start ...");

List<Document> docList = query(params);

logger.info("query docment done, size:" + docList.size())
```

## 使用log4j的MDC打印用户名等额外信息

有时候，业务量大的系统要找到某一个用户的操作日志定位问题非常痛苦，每一个日志上加用户名又低效也容易漏掉，所以我们要在更高层级上解决这些共性问题。

我们使用log4j的MDC功能达成这个目的。

在单点登录后，设置用户信息的时候，把用户标志放到MDC中。

```Java
private final static ThreadLocal<String> tlUser = new ThreadLocal<>();

public static final String KEY_USER = "user";

public static void setUser(String userid) {
  tlUser.set(userid);
  
  // 把用户信息放到log4j
  MDC.put(KEY_USER, userid);
}
```

然后修改log4j配置，pattern上增加 `%X{user}` ，位置随意。

增加线程相关配置 `[%t]` ，完整参数详见[log4j变量](https://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html)

```XML
<layout class="org.apache.log4j.PatternLayout">
  <param name="ConversionPattern" value="[%t]%-d{MM-dd HH:mm:ss,SSS} %-5p: %X{user} - %c - %m%n" />/>
</layout>
```


![日志](/pictures/log1.png) 

> 没有用户信息的时候并不会报错，而是空串。
> 不要一开始就关注日志级别和日志性能，规则越多越难落地。

# 优雅编码 - 参数校验和国际化规范

请阅读 [我的编码习惯 - 参数校验和国际化规范](https://zhuanlan.zhihu.com/p/29129469) 。

1. 调用自己的校验函数，参数上不要出现local，messagesource
2. 不正确的参数值要提示出来，减少定位时间
3. 国际化参数不要放到每一个url上


# 技术点总结

1. spring的aop的使用
2. log4j的MDC使用
3. JDK的ThreadLocal的使用

# 对开发组长的要求

定义好代码框架，不要做太多、太细的要求，否则无法落地。这篇文章中，规范中要求做到的少，不准做的多，落地相对容易。

1. 定义好统一的接口格式、异常、常量等
2. 多使用AOP和ThreadLocal简化代码
3. 亲自编写校验函数和工具类
4. 代码评审中，严格控制函数的参数，不允许出现复杂参数和业务无关的参数

# 对开发人员的建议

1. 不要养成面对debug编程，用日志代替debug
2. 不要一上来就做整个功能测试，要一行一行代码一个一个函数测试
