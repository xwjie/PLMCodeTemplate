package plm.services;

import static plm.common.utils.CheckUtil.*;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import plm.beans.Config;
import plm.common.exceptions.CheckException;
import plm.common.utils.UserUtil;
import plm.daos.ConfigDao;

/**
 * ！！！错误代码示例
 * 
 * @author 肖文杰 https://xwjie.github.io/PLMCodeTemplate/
 *
 */
@Service
public class ConfigService2 {

  private static final Logger logger = LoggerFactory
      .getLogger(ConfigService2.class);

  @Autowired
  ConfigDao dao;

  @Autowired
  MessageSource messageSource;

  public Collection<Config> getAll() {
    return dao.getAll();
  }

  /**
   * ！！！错误示范
   * 
   * 出现和业务无关的参数local
   * 
   * @param id
   * @param locale
   * @return
   */
  public boolean delete(long id, Locale locale) {
    // 参数校验
    if (id <= 0L) {
      if (locale.equals(Locale.CHINESE)) {
        throw new CheckException("非法的ID：" + id);
      } else {
        throw new CheckException("Illegal ID:" + id);
      }
    }

    boolean result = dao.delete(id);

    // 修改操作需要打印操作结果
    logger.info("delete config success, id:" + id + ", result:" + result);

    return dao.delete(id);
  }

  public void doSome() {

  }

  /**
   * ！！！错误的示例
   * 
   * @param request
   * @return
   */
  public String add(HttpServletRequest request) {
    Map<String, Object> data = new HashMap<String, Object>();

    try {
      String name = (String) request.getParameter("name");
      String value = (String) request.getParameter("value");

      // 示例代码
      long newID = add(name, value);

      data.put("code", 0);
      data.put("newID", newID);
    } catch (CheckException e) {
      // 参数等校验出错，已知异常，不需要打印堆栈，返回码为-1
      data.put("code", -1);
      data.put("msg", e.getMessage());
    } catch (Exception e) {
      // 其他未知异常，需要打印堆栈分析用，返回码为99
      logger.error("add config error", e);

      data.put("code", 99);
      data.put("msg", e.toString());
    }

    return JSONObject.toJSONString(data);
  }

  private long add(String name, String value) {
    logger.info("add config ,name:" + name + ", value:" + value);
    // XXX
    return 1L;
  }

  /**
   * ！！！错误代码示例
   * 
   * 1. 和业务无关的参数locale，messagesource 2. 输入输出都是map，根本不知道输入了什么，返回了什么
   * 
   * @param params
   * @return
   */
  public Map<String, Object> addConfig(Map<String, Object> params) {

    Map<String, Object> data = new HashMap<String, Object>();

    // 语言（中英文提示不同）
    Locale locale = null;

    try {
      String lang = (String) params.get("lang");
      locale = "zh".equalsIgnoreCase(lang) ? Locale.CHINESE : Locale.ENGLISH;

      String name = (String) params.get("name");
      String value = (String) params.get("value");

      // 示例代码，省略其他代码
    } catch (Exception e) {
      logger.error("add config error", e);

      data.put("code", 99);
      data.put("msg", messageSource.getMessage("SYSTEMERROR", null, locale));
    }

    return data;
  }

  public void updateUser(Map<String, Object> params) {
    long userId = (Long) params.get("id");
    String nickname = (String) params.get("nickname");

    // 更新代码
  }

  public void updateUserNickName(long userId, String nickname) {
    // 更新代码
  }

  public boolean add(Map<String, Object> params) {
    String name = (String) params.get("name");
    String value = (String) params.get("value");

    // 执行新增操作

    return true;
  }

  /**
   * !!错误范例：随便捕获异常加空判断，代码最大的隐患
   * 
   * @param id
   * @param value
   */
  public void updateConfigValue(long id, String value) {
    Config config = null;

    try {
      config = findConfigById(id);
    } catch (Exception e) {
      logger.error("findConfigByName error", e);
    }

    if (config != null) {
      config.setValue(value);

      // 执行数据库保存操作
      doSave(config);
    }
  }

  /**
   * 正确写法
   * 
   * @param id
   * @param value
   */
  public void updateConfigValue2(long id, String value) {
    Config config = findConfigById(id);

    config.setValue(value);

    // 执行数据库保存操作
    doSave(config);
  }

  private void doSave(Config config) {
    // TODO Auto-generated method stub
  }

  private Config findConfigById(long id) throws CheckException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * 错误演示：配置代码和业务代码耦合
   */
  public void someServiceCode() {
    // 从某个地方得到配置的json对象
    JSONObject configJson = readConfig();

    // 如果某个配置项为true
    if (configJson.getBooleanValue("someFlag")) {
      // 执行某些操作
    }

  }

  private JSONObject readConfig() {
    // TODO Auto-generated method stub
    return null;
  }

}
