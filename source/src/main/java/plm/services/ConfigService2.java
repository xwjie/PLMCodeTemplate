package plm.services;

import static plm.common.utils.CheckUtil.*;

import java.util.Collection;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;

import plm.beans.Config;
import plm.daos.ConfigDao;

@Service
public class ConfigService2 {

	private static final Logger logger = LoggerFactory.getLogger(ConfigService2.class);

	@Autowired
	ConfigDao dao;

	public Collection<Config> getAll() {
		return dao.getAll();
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
		
		JsonNode json = null;
		
		

	}
	

}
