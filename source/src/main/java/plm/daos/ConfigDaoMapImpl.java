package plm.daos;

import static plm.common.utils.CheckUtil.check;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import plm.beans.Config;

/**
 * 使用map实现的示例
 * 
 * @author 肖文杰
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

	@Override
	public boolean delete(long id) {
		return configs.remove(id) != null;
	}

}
