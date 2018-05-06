package plm;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import plm.beans.Config;
import plm.common.exceptions.CheckException;
import plm.common.utils.UserUtil;
import plm.services.ConfigService;

/**
 * SpringMVC测试代码
 * 
 * 需要加入依赖：tomcat-embed-core
 * 
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CongfigServiceTest {

  private static final String CONFIG_NAME = "配置项名称";

  @Autowired
  ConfigService configService;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * 初始化信息
   */
  @Before
  public void init() {
    System.out.println("------------init-----------");
    UserUtil.setLocale("zh");
    UserUtil.setUser("测试的用户");
  }

  @Test
  public void test0_Full() {
    Config config = new Config();

    config.setName(CONFIG_NAME);
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

    // 查询测试
    Collection<Config> all2 = configService.getAll();
    assertTrue(all2.size() == 0);
  }

  @Test
  public void test1_addConfigException() {
    System.out.println("\n\n--测试[参数为空]---\n\n");

    thrown.expect(CheckException.class);
    thrown.expectMessage("参数为空");

    configService.add(null);
  }

  @Test
  public void test2_addConfigException() {
    System.out.println("\n\n--测试[取值为空]---\n\n");

    thrown.expect(CheckException.class);
    thrown.expectMessage("取值为空");

    Config config = new Config();

    config.setName(CONFIG_NAME);
    config.setValue(null);

    configService.add(config);
  }

  @Test
  public void test3_addConfigException() {
    // 先创建数据
    {
      Config config = new Config();

      config.setName(CONFIG_NAME);
      config.setValue("配置项值");

      // 新增测试
      long newId = configService.add(config);
      assertTrue(newId > 1);
    }

    // 再重复创建
    {
      System.out.println("\n\n--测试[名称已经存在]---\n\n");

      thrown.expect(CheckException.class);
      thrown.expectMessage("名称已经存在");

      Config config = new Config();

      config.setName(CONFIG_NAME);
      config.setValue("https://github.com/xwjie");

      configService.add(config);
    }
  }

}
