package com.wyj.ytyn.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 登录控制Controller
 */

@RestController
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        log.info("login");
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest req, Map<String, Object> model) {
        log.info("进入登录处理");
        String name = req.getParameter("userName");
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        log.info("exceptionClassName:" + exceptionClassName);
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户已锁定或删除";
        } else if (exceptionClassName != null) {
            error = "其他错误:" + exceptionClassName;
        }

        if (SecurityUtils.getSubject().isAuthenticated()) {
            model.put("name", req.getParameter("userName"));
            return "index";
        } else {
            model.put("error", error);
            return "login";
        }
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


}
