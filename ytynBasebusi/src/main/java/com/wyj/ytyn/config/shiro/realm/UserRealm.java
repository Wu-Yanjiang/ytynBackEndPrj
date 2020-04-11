package com.wyj.ytyn.config.shiro.realm;

import com.wyj.ytyn.entity.SysUser;
import com.wyj.ytyn.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm处理登录 权限
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private ISysMenuService menuService;


    /**
     * 授权
     * @param arg0
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        SysUser user = null;
        Object obj = SecurityUtils.getSubject();
        if (null != obj) {
            user = new SysUser();
            BeanUtils.copyProperties(user, obj);
        }
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
