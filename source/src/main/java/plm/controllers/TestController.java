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

import plm.common.exceptions.CheckException;
import plm.services.ConfigService2;

/**
 * ！！！错误示范！！！
 * 
 * @author 肖文杰
 *
 */
@RequestMapping("/config2")
@RestController
public class TestController {
	//
	@Autowired
	ConfigService2 configService;

	private static final Logger log = LoggerFactory.getLogger(ConfigService2.class);

	//
	@PostMapping("/delete")
	public Map<String, Object> delete(long id, String lang) {
		Map<String, Object> data = new HashMap<String, Object>();

		boolean result = false;
		try {
			// 语言（中英文提示不同）
			Locale local = "zh".equalsIgnoreCase(lang) ? Locale.CHINESE : Locale.ENGLISH;

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
	 * ！！！错误规范
	 * 增加配置
	 */
	@PostMapping("/add")
	public void addConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		
		//返回了一个json格式的字符串。。。
		String result = configService.add(request);
		
		response.getOutputStream().write(result.getBytes());
		response.getOutputStream().flush();
	}
}
