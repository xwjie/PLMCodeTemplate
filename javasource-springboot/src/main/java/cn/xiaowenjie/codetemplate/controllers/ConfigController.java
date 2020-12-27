package cn.xiaowenjie.codetemplate.controllers;

import cn.xiaowenjie.codetemplate.common.annotations.Log;
import cn.xiaowenjie.codetemplate.common.beans.ResultBean;
import cn.xiaowenjie.codetemplate.common.consts.LogConst;
import cn.xiaowenjie.codetemplate.entity.Config;
import cn.xiaowenjie.codetemplate.services.ConfigService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * 配置对象处理器
 *
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
@Api(description = "配置管理")
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
        return new ResultBean<>(configService.getAll());
    }

    @GetMapping("/count")
    @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_CONFIG)
    public ResultBean<Long> count() {
        return new ResultBean<>(configService.count());
    }

    /**
     * 新增数据, 返回新对象的id
     *
     * @param config
     * @return
     */
    @ApiOperation("添加配置")
    @ApiOperationSupport(ignoreParameters = {"config.id", "config.createTime", "config.updateTime", "config.creator"})
    @PostMapping("/add")
    @Log(action = LogConst.ACTION_ADD, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#config.name")
    public ResultBean<Long> add(@RequestBody Config config) {
        return new ResultBean<>(configService.add(config));
    }

    /**
     * 根据id删除对象
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @Log(action = LogConst.ACTION_DELETE, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#id")
    public ResultBean<Boolean> delete(int id) {
        return new ResultBean<>(configService.delete(id));
    }

    @PostMapping("/update")
    @Log(action = LogConst.ACTION_UPDATE, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#config.name", param = "#config")
    public ResultBean<Boolean> update(Config config) {
        configService.update(config);
        return new ResultBean<>(true);
    }
}
