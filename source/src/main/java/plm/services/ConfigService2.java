package plm.services;

import static plm.common.utils.CheckUtil.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import plm.beans.Config;
import plm.common.exceptions.CheckException;
import plm.daos.ConfigDao;

@Service
public class ConfigService2 {

	private static final Logger logger = LoggerFactory.getLogger(ConfigService2.class);

	@Autowired
	ConfigDao dao;

	public Collection<Config> getAll() {
		return dao.getAll();
	}

	public boolean delete(long id, Locale local) {
		// 参数校验
		if (id <= 0L) {
			if (local.equals(Locale.CHINESE)) {
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

	public long add(Config config, Locale locale) {
		// 参数校验
		notNull(config, "param.is.null");
		notEmpty(config.getName(), "name.is.null");
		notEmpty(config.getValue(), "value.is.null");

		// 校验通过后打印重要的日志
		logger.info("add config:" + config);

		long newId = dao.add(config);

		// 修改操作需要打印操作结果
		logger.info("add config success, id:" + newId);

		return newId;
	}

	public boolean delete(long id) {
		// 参数校验
		check(id > 0L, "id.error");

		// 校验通过后打印重要的日志
		logger.info("delete config, id:" + id);

		boolean result = dao.delete(id);

		// 修改操作需要打印操作结果
		logger.info("delete config success, id:" + id, ", result:" + result);

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

			//示例代码
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
		//XXX
		return 1L;
	}

}
