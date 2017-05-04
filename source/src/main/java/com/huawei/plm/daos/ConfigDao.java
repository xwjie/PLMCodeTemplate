package com.huawei.plm.daos;

import java.util.Collection;

import com.huawei.plm.beans.Config;

public interface ConfigDao {

	Collection<Config> getAll();

	long add(Config config);

	boolean delete(long id);

}
