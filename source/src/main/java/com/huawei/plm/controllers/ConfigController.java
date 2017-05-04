package com.huawei.plm.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.plm.beans.Config;
import com.huawei.plm.common.beans.ResultBean;
import com.huawei.plm.services.ConfigService;

@RequestMapping("/config")
@RestController
public class ConfigController {

	@Autowired
	ConfigService configService;

	@GetMapping("/all")
	public ResultBean<Collection<Config>> getAll() {
		return new ResultBean<Collection<Config>>(configService.getAll());
	}

	@PostMapping("/add")
	public ResultBean<Long> add(Config config) {
		return new ResultBean<Long>(configService.add(config));
	}

	@PostMapping("/delete")
	public ResultBean<Boolean> delete(long id) {
		return new ResultBean<Boolean>(configService.delete(id));
	}
}
