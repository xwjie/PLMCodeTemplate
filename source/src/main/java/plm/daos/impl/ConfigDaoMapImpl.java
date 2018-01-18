package plm.daos.impl;

import static plm.common.utils.CheckUtil.check;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import plm.beans.Config;
import plm.common.utils.UserUtil;
import plm.daos.ConfigDao;

/**
 * 使用map实现的示例
 * 
 * @author 肖文杰  https://xwjie.github.io/PLMCodeTemplate/
 */
@Component
public class ConfigDaoMapImpl implements ConfigDao {

	private final ConcurrentSkipListMap<Long, Config> configs = new ConcurrentSkipListMap<Long, Config>();

	private static final AtomicLong idSequence = new AtomicLong(1000L);

	@PostConstruct
	public void init() {

	}

	@Override
	public Collection<Config> getAll() {
		return configs.values();
	}

	@Override
	public long add(Config config) {
		// 检查名称是否重复
		check(null == getByName(config.getName()), "name.repeat");

		// 创建用户
		config.setCreator(UserUtil.getUser());

		long id = idSequence.incrementAndGet();

		config.setId(id);

		configs.put(id, config);

		return id;
	}

	private Config getByName(String name) {
		Collection<Config> values = configs.values();

		for (Config config : values) {
			if (config.getName().equalsIgnoreCase(name)) {
				return config;
			}
		}

		return null;
	}

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

	/**
	 * 判断逻辑变化可能性大，抽取一个函数
	 * 
	 * @param config
	 * @return
	 */
	private boolean canDelete(Config config) {
		return UserUtil.getUser().equals(config.getCreator());
	}

}
