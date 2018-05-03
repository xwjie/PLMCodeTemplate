package plm.services;

import static plm.common.utils.CheckUtil.check;
import static plm.common.utils.CheckUtil.notEmpty;
import static plm.common.utils.CheckUtil.notNull;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import plm.beans.Config;
import plm.daos.ConfigDao;

/**
 * 处理配置项的服务类
 * 
 * @author 晓风轻 https://xwjie.github.io/PLMCodeTemplate/
 */
@Service
public class ConfigService {

  private static final Logger logger = LoggerFactory
      .getLogger(ConfigService.class);

  private final ConfigDao dao;

  public ConfigService(ConfigDao configDao) {
    this.dao = configDao;
  }

  public Collection<Config> getAll() {
    // 校验通过后打印重要的日志
    logger.info("getAll start ...");

    Collection<Config> data = dao.getAll();

    logger.info("getAll end, data size:{}", data.size());

    return data;
  }

  public long add(Config config) {
    // 参数校验
    notNull(config, "param.is.null");
    notEmpty(config.getName(), "name.is.null");
    notEmpty(config.getValue(), "value.is.null");

    // 校验通过后打印重要的日志
    logger.info("add config:" + config);

    long newId = dao.add(config);

    // 修改操作需要打印操作结果
    logger.info("add config success, id:{}", newId);

    return newId;
  }

  public boolean delete(long id) {
    // 参数校验
    check(id > 0L, "id.error", id);

    boolean result = dao.delete(id);

    // 修改操作需要打印操作结果
    logger.info("delete config success, id: {}, result: {}", id, result);

    return result;
  }

  /**
   * 打印日志示例代码
   * 
   * @param id
   * @return
   */
  public boolean someOpration(long id) {
    // XXX 示例代码
    int opType = getSomeFlag();

    // 校验通过后打印重要的日志
    logger.info("someOpration, id: {}, opType: {}", id, opType);

    boolean result = false;

    if (opType == 1) {
      // 做这些事情
    } else {
      // 做那些事情
    }

    // 修改操作需要打印操作结果
    logger.info("someOpration success, id: {}, result: {}", id, result);

    return result; // 示例代码
  }

  private int getSomeFlag() {
    return 2;
  }

  public void update(Config config) {

  }

}
