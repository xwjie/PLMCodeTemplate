package plm.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import plm.beans.Config;
import plm.common.annotations.Log;
import plm.common.beans.ResultBean;
import plm.consts.LogConst;
import plm.services.ConfigService;

/**
 * 配置对象处理器
 * 
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
@RequestMapping("/config")
@RestController
public class ConfigController {

  private final ConfigService configService;

  public ConfigController(ConfigService configService) {
    this.configService = configService;
  }

  @GetMapping("/all")
  @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_CONFIG)
  public ResultBean<Collection<Config>> getAll() {
    return new ResultBean<Collection<Config>>(configService.getAll());
  }

  /**
   * 新增数据, 返回新对象的id
   * 
   * @param config
   * @return
   */
  @PostMapping("/add")
  @Log(action = LogConst.ACTION_ADD, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#config.name")
  public ResultBean<Long> add(Config config) {
    return new ResultBean<Long>(configService.add(config));
  }

  /**
   * 根据id删除对象
   * 
   * @param id
   * @return
   */
  @PostMapping("/delete")
  @Log(action = LogConst.ACTION_DELETE, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#id")
  public ResultBean<Boolean> delete(long id) {
    return new ResultBean<Boolean>(configService.delete(id));
  }

  @PostMapping("/update")
  @Log(action = LogConst.ACTION_UPDATE, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#config.name", param ="#config")
  public ResultBean<Boolean> update(Config config) {
    configService.update(config);
    return new ResultBean<Boolean>(true);
  }
}
