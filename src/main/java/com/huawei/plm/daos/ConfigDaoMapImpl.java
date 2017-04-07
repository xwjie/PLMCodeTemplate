package com.huawei.plm.daos;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.huawei.plm.beans.Config;

@Component
public class ConfigDaoMapImpl implements ConfigDao {

	private final ConcurrentSkipListMap<Long, Config> configs = new ConcurrentSkipListMap<>();

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
		long id = idSequence.get();

		config.setId(id);

		configs.put(id, config);

		return id;
	}

	@Override
	public boolean delete(long id) {
		return configs.remove(id) != null;
	}

}
