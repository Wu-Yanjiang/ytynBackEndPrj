package com.wyj.ytyn.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户处理Controller
 */

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public String getList() {
        log.info("进入用户列表");
        return "user/list";
    }

    @RequiresPermissions(value = {"user:add"})
    @GetMapping("/add")
    public String getAdd() {
        log.info("进入新增页面");
        return "user/add";
    }

}
