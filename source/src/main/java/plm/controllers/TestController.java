package plm.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import plm.beans.Config;
import plm.common.beans.ResultBean;
import plm.common.exceptions.CheckException;
import plm.services.ConfigService;
import plm.services.ConfigService2;
import sun.util.logging.resources.logging;

@RequestMapping("/config2")
@RestController
public class TestController {
	//
	@Autowired
	ConfigService configService;

	private static final Logger log = LoggerFactory.getLogger(ConfigService2.class);

	//
	// @GetMapping("/all")
	// public ResultBean<Collection<Config>> getAll() {
	// return new ResultBean<Collection<Config>>(configService.getAll());
	// }
	//
	// @PostMapping("/add")
	// public ResultBean<Long> add(Config config) {
	// return new ResultBean<Long>(configService.add(config));
	// }
	//
	// @PostMapping("/delete")
	// public ResultBean<Boolean> delete2(long id) {
	// return new ResultBean<Boolean>(configService.delete(id));
	// }
	//
	// @PostMapping("/update")
	// public Map<String, Object> update(long id, String jsonStr) {
	//
	// }
	//
	// @PostMapping("/delete")
	// public Object delete(long id, String lang) {
	// try {
	// boolean result = configService.delete(id, local);
	// return result;
	// } catch (Exception e) {
	// log.error(e);
	// return e.toString();
	// }
	// }
	//
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
}
