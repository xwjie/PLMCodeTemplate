package plm;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import plm.beans.Config;
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
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class CongfigServiceTest {

	@Autowired
	ConfigService configService;

	@Before
	public void test() {
		UserUtil.setLocale("cn");
		UserUtil.setUser("测试的用户");
	}

	@Test
	public void testFull() {
		Config config = new Config();

		config.setName("配置项名称");
		config.setValue("配置项值");

		// 新增配置项测试
		long newId = configService.add(config);
		assertTrue(newId > 1);

		// 再次新增，报错
	}

}
