package com.wyj.ytyn.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限操作Controller
 */

@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @RequestMapping("updatePermission")
    public Object updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
                PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
                DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

                // 清空旧的权限控制
                manager.getFilterChains().clear();
                shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
                // 后面的可以从数据库读取
                LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
                // 按顺序判断
                filterChainDefinitionMap.put("/static/**", "anon");
                //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
                filterChainDefinitionMap.put("/logout", "logout");
                //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
                /************************************初始化所有的权限信息开始******************************************/
                //这里，如果以后再项目中使用的话，直接从数据库中查询
                filterChainDefinitionMap.put("/user/list", "authc,perms[user:list]");
                filterChainDefinitionMap.put("/user/add", "authc,perms[user:add]");
                /***************************************初始化所有的权限信息开始结束*********************************************/
                filterChainDefinitionMap.put("/**", "authc");
                //
                shiroFilterFactoryBean.setLoginUrl("/login");
                // 登录成功后要跳转的链接
                shiroFilterFactoryBean.setSuccessUrl("/index");
                //未授权界面
                shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
                shiroFilterFactoryBean
                        .setFilterChainDefinitionMap(filterChainDefinitionMap);
                // 重新构建生成
                Map<String, String> chains = shiroFilterFactoryBean
                        .getFilterChainDefinitionMap();
                for (Map.Entry<String, String> entry : chains.entrySet()) {
                    String url = entry.getKey();
                    String chainDefinition = entry.getValue().trim()
                            .replace(" ", "");
                    manager.createChain(url, chainDefinition);
                }


                return "更新权限成功";
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("更新Shiro权限出现错误");
            }
        }
    }

}
