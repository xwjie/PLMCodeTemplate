package plm.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import plm.beans.Config;
import plm.common.beans.ResultBean;
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
  public ResultBean<Boolean> delete(long id) {
    return new ResultBean<Boolean>(configService.delete(id));
  }

  @PostMapping("/update")
  public ResultBean<Boolean> update(Config config) {
    configService.update(config);
    return new ResultBean<Boolean>(true);
  }
}
