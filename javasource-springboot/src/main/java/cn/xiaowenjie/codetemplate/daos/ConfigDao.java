package cn.xiaowenjie.codetemplate.daos;

import cn.xiaowenjie.codetemplate.beans.Config;

import java.util.Collection;

/**
 * 配置对象的crud接口
 * 
 * @author 晓风轻  https://xwjie.github.io/PLMCodeTemplate/
 */
public interface ConfigDao {

	Collection<Config> getAll();

	long add(Config config);

	boolean delete(long id);

}
