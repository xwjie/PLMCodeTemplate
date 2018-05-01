package plm.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import plm.common.exceptions.CheckException;
import plm.services.ConfigService2;

/**
 * ！！！错误示范！！！
 * 
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 *
 */
@RequestMapping("/config2")
@RestController
public class BadCodeConfigController {
  //
  @Autowired
  ConfigService2 configService;

  private static final Logger log = LoggerFactory
      .getLogger(ConfigService2.class);

  /**
   * 成功返回boolean，失败返回string，大忌 出现无关的参数：lang
   * 
   * @param id
   * @param lang
   * @return
   */
  @PostMapping("/delete2")
  public Object delete2(long id, String lang) {
    try {
      // 语言（中英文提示不同）
      Locale local = "zh".equalsIgnoreCase(lang) ? Locale.CHINESE
          : Locale.ENGLISH;
      boolean result = configService.delete(id, local);
      return result;
    } catch (Exception e) {
      log.error("delete error", e);
      return e.toString();
    }
  }

  /**
   * 错误范例，输入输出都是复杂参数
   * 
   * @param params
   * @return
   */
  @PostMapping("/add")
  public Map<String, Object> add(Map<String, Object> params) {
    Map<String, Object> data = new HashMap<String, Object>();

    boolean result = false;

    try {
      // 语言（中英文提示不同）
      result = configService.add(params);
      data.put("code", 0);
    } catch (CheckException e) {
      // 参数等校验出错，已知异常，不需要打印堆栈，返回码为-1
      data.put("code", -1);
      data.put("msg", e.getMessage());
    } catch (Exception e) {
      // 其他未知异常，需要打印堆栈分析用，返回码为99
      log.error("add config error", e);

      data.put("code", 99);
      data.put("msg", e.toString());
    }

    data.put("result", result);

    return data;
  }

  /**
   * 错误范例，输入json格式复杂参数，没有考虑失败
   * 
   * @param jsonStr
   * @return
   */
  @PostMapping("/update")
  public void update(long id, String jsonStr) {

    JSONObject params = JSON.parseObject(jsonStr);

    // 其他操作
  }

  /**
   * 根据id删除对象
   * 
   * @param id
   * @param lang
   * @return
   */
  @PostMapping("/delete")
  public Map<String, Object> delete(long id, String lang) {
    Map<String, Object> data = new HashMap<String, Object>();

    boolean result = false;
    try {
      // 语言（中英文提示不同）
      Locale local = "zh".equalsIgnoreCase(lang) ? Locale.CHINESE
          : Locale.ENGLISH;

      result = configService.delete(id, local);

      data.put("code", 0);

    } catch (CheckException e) {
      // 参数等校验出错，已知异常，不需要打印堆栈，返回码为-1
      data.put("code", -1);
      data.put("msg", e.getMessage());
    } catch (Exception e) {
      // 其他未知异常，需要打印堆栈分析用，返回码为99
      log.error("delete config error", e);

      data.put("code", 99);
      data.put("msg", e.toString());
    }

    data.put("result", result);

    return data;
  }

  /**
   * ！！！错误规范 增加配置
   */
  @PostMapping("/add2")
  public void add2(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("application/json;charset=UTF-8");

    // 返回了一个json格式的字符串。。。
    String result = configService.add(request);

    response.getOutputStream().write(result.getBytes());
    response.getOutputStream().flush();
  }
}
