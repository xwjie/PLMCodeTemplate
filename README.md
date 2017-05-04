# PLMCodeTemplate
给部门制定的代码框架模板

# 前言



# 目录

> * 优雅编码 - 去掉方法中的非业务参数（如user，local等）
> * 优雅编码 - 参数校验和异常处理
> * 优雅编码 - 日志打印
> * 优雅编码 - ResultBean的重要性和约束
> * 规范总结
> * 技术点总结

> * 优雅编码 - 去掉方法中的非业务参数（如user，local等）


# 优雅编码 - 日志打印

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

```XML
<layout class="org.apache.log4j.PatternLayout">
	<param name="ConversionPattern" value="%-5p: %X{user} - %c - %m%n" />
</layout>
```

![日志](/pictures/log1.png) 

> 没有用户信息的时候并不会报错，而是空串。

## 编程规范
1. 所有的接口返回ResultBean，并且ResultBean只允许出现在Controller，不允许传到services和dao里面（太影响可读性了）
2. 

## 接口规范
1. 所有接口都必须返回ResultBean，就是说一开始就考虑好成功和失败的场景。
2. ResultBean只允许出现在controller，不允许到处传。
3. 对外接口可以考虑使用细分错误码，对内接口使用异常信息即可

## 日志打印规范
1. 关键参数必须打印日志
2. 对一个集合处理的时候必须打印处理的数据量
3. 可能耗时长的函数需要在前后打印日志以方便收集耗时
4. 

# 技术点总结

> spring的aop的使用
> log4j的MDC使用
> JDK的ThreadLocal的使用

