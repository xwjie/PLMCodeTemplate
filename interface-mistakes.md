# 定义接口容易犯的错误

## 1. **返回格式不统一**

同一个系统/模块应该返回相同的接口定义，这样前后台才好处理。

避免出现同一个接口，有时候返回数组，有时候返回单个；成功的时候返回对象，失败的时候返回错误信息字符串。

**错误**范例：

```java
// 成功返回boolean，失败返回string，大忌
@PostMapping("/delete")
public Object delete(long id, String lang) {
  try {
    boolean result = configService.delete(id, local);
    return result;
  } catch (Exception e) {
    log.error(e);
    return e.toString();
  }
}
```

## **2. 返回复杂对象**

最典型的就是返回一个Map，可读性不好，只有阅读完整的代码才知道返回了什么字段。

**错误**范例：

```java
// 返回Map，可读性不好，不知道返回什么什么字段
@PostMapping("/delete")
public Map<String, Object> delete(long id){

}
```

## **3. 出现和业务无关的输入参数**

如语言，当前用户信息 都不应该出现参数里面，应该从**当前会话**里面获取。

这些参数主要有几点问题：

1. 可读性不好
2. 测试的时候不好构建这类参数
3. 有些参数如当前用户信息的，容易被修改导致出现问题

**错误**范例：

```java
// （当前用户删除数据）参数出现lang和userid，尤其是userid，大忌
@PostMapping("/delete")
public Map<String, Object> delete(String userId, long id, String lang){

}
```

## **4. 出现复杂的输入参数**

一般情况下，不允许出现例如json字符串，Map， Request 这样的参数，这种参数可读性极差，完全不知道前台传入了什么字段。应该定义对应的bean。

**错误**范例：

```java
// 参数出现json格式字符串，可读性不好
@PostMapping("/update")
public Map<String, Object> update(long id, String jsonStr) {

}

// 参数出现Map，可读性不好
@PostMapping("/update")
public Map<String, Object> update(long id, Map<String, Object> params){

}

// 参数出现request，可读性不好
@PostMapping("/update")
public Map<String, Object> update(HttpRequest request){

}
```

## 5**. 没有考虑失败情况**

接口定义应该一开始就考虑到成功和失败的场景。如果只考虑成功场景，等后面测试发现有错误情况，这个时候需要返回错误信息，就会导致返工。

**错误**范例：

```java
//不返回任何数据，没有考虑失败场景，容易返工
@PostMapping("/update")
public void update(long id, xxx) {

}
```

## 6**. 没有返回应该返回的数据**

这是一些约定俗成的习惯。如：新增接口一般情况下应该返回新对象的id标识，如果新增接口只返回boolean，容易导致返工。

**错误**范例：

```java
// 约定俗成，新建应该返回新对象的信息，只返回boolean容易导致返工
@PostMapping("/add")
public boolean add(xxx) {
  //xxx
  return configService.add();
}
```



