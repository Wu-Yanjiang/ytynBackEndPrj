package com.wyj.ytyn.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Shiro的配置类
 */
@Configuration
@Slf4j
public class ShiroConfiguration {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info("ShiroFilter...");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置不需要拦截的路径
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 按顺序依次判断
        filterChainDefinitionMap.put("/static/**", "anon");
        // 配置退出 过滤器， 具体的退出代码框架已默认实现
        filterChainDefinitionMap.put("/logout", "logout");

        //****************初始化权限信息开始*************
        // authc: 认证访问      anon: 匿名访问
        // 可以直接在数据库配置
        filterChainDefinitionMap.put("/user/list", "authc,perms[user:list]");
//        filterChainDefinitionMap.put("/user/add", "authc,perms[user:add]");
        //****************初始化权限信息结束*************
        filterChainDefinitionMap.put("/**", "authc");
        // 不设置会默认寻找web工程根目录的/login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后的跳转链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        // 后期可以迭代缓存机制
        return new MyShiroRealm();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();;
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
