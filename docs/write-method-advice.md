# 函数编写建议

> **傻瓜都能写出计算机可以读懂的代码，只有优秀的程序员才能写出人能读懂的代码！**

函数编写，**可读性**放在第一位。而函数可读性的最关键点在于函数的**输入参数**。

## **1. 不要出现和业务无关的参数**

函数参数里面不要出现local，messagesource，request，response这些参数，第一非常干扰阅读，一堆无关的参数把业务代码都遮掩住了，第二导致你的函数不好测试，如你要构建一个request参数来测试，还是有一定难度的。

干净清爽的参数，写测试代码非常舒服，如我们编写一些Service的测试代码：

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CongfigServiceTest {

    @Autowired
    ConfigService configService;

    /**
     * 初始化信息
     */
    @Before
    public void init() {
        System.out.println("------------init-----------");
        UserUtil.setLocale("en");
        UserUtil.setUser("测试的用户");
    }

    @Test
    public void test01Full() {
        Config config = new Config();

        config.setName("配置项名称");
        config.setValue("配置项值");

        // 新增测试
        long newId = configService.add(config);
        assertTrue(newId > 1);

        // 查询测试
        Collection<Config> all = configService.getAll();
        assertTrue(all.size() == 1);

        // 删除测试
        boolean result = configService.delete(newId);
        assertTrue(result);
    }
}
```

## **2. 避免使用复杂的数据对象作为参数和结果**

输入输出参数都应该尽量避免出现Map，Json这种“黑箱子”参数。这种参数，你只有通读代码，才知道里面究竟放了什么。

**错误**代码范例：

```java
/**
 * ！！！错误代码示例
 * 1. 和业务无关的参数locale，messagesource
 * 2. 输入输出都是map，根本不知道输入了什么，返回了什么
 * 
 * @param params
 * @param local
 * @param messageSource
 * @return
 */
public Map<String, Object> addConfig(Map<String, Object> params, 
    Locale locale, MessageSource messageSource) {

  Map<String, Object> data = new HashMap<String, Object>();

  try {
    String name = (String) params.get("name");
    String value = (String) params.get("value");

    //示例代码，省略其他代码
  }
  catch (Exception e) {
    logger.error("add config error", e);

    data.put("code", 99);
    data.put("msg", messageSource.getMessage("SYSTEMERROR", null, locale));
  }

  return data;
}
```

## **3. 有明确的输入输出和方法名**

尽量有清晰的输入输出参数，使人一看就知道函数做了啥。举例：

```java
public void updateUser(Map<String, Object> params){
  long userId = (Long) params.get("id");
  String nickname = (String) params.get("nickname");

  //更新代码
}
```

上面的函数，看函数定义你只知道更新了用户对象，但你不知道更新了用户的什么信息。**建议**写成下面这样：

```java
public void updateUserNickName(long userId, String nickname){
  //更新代码
}
```

就算不看方法名，只看参数就能知道这个函数只更新了nickname一个字段。

## **4. 把可能变化的地方封装成函数**

编写函数的总体指导思想是**抽象和封装**，需要把代码的逻辑抽象出来封装成为一个函数，以应对将来可能的变化。以后代码逻辑有变更的时候，单独修改和测试这个函数即可。

如何**识别可能变的地方**，多思考一下就知道了，随着工作经验的增加识别起来会越来越容易。比如，开发初期，业务说只有管理员才可以删除某个对象，你就应该考虑到后面可能除了管理员，其他角色也可能可以删除，或者说对象的创建者也可以删除，这就是将来潜在的变化，你写代码的时候就要**埋下伏笔**，把是否能删除做成一个函数。后面需求变更的时候，你就只需要改一个函数。

举例，删除配置项的逻辑，判断一下只有是自己创建的配置项才可以删除，一开始代码是这样的：

```java
/**
 * 删除配置项
 */
@Override
public boolean delete(long id) {
  Config config = configs.get(id);

  if(config == null){
    return false;
  }

  // 只有自己创建的可以删除
  if (UserUtil.getUser().equals(config.getCreator())) {
    return configs.remove(id) != null;      
  }

  return false;
}
```

这里我们会识别一下，是否可以删除这个地方就有可能会变化，很有可能以后管理员就可以删除任何人的，那么这里就抽成一个函数：

```java
/**
 * 删除配置项
 */
@Override
public boolean delete(long id) {
  Config config = configs.get(id);

  if(config == null){
    return false;
  }

  // 判断是否可以删除
  if (canDelete(config)) {
    return configs.remove(id) != null;      
  }

  return false;
}

/**
 * 判断逻辑变化可能性大，抽取一个函数
 * 
 * @param config
 * @return
 */
private boolean canDelete(Config config) {
  return UserUtil.getUser().equals(config.getCreator());
}
```

后来想了一下，没有权限应该抛出异常，再次修改为：

```java
/**
 * 删除配置项
 */
@Override
public boolean delete(long id) {
  Config config = configs.get(id);

  if (config == null) {
    return false;
  }

  // 判断是否可以删除
  check(canDelete(config), "no.permission");

  return configs.remove(id) != null;
}
```

这就是简单的抽象和封装的思想。**把可能变化的点封装成可以独立测试的函数**，我们编码过程中就会少了很多“需求变更”。

