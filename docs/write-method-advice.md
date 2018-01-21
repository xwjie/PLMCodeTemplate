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





