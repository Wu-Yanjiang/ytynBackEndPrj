package com.wyj.ytyn.config.shiro.realm;

import com.wyj.ytyn.entity.SysUser;
import com.wyj.ytyn.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

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
        // 角色列表
        HashSet<String> roles = new HashSet<>();
        // 功能列表
        HashSet<String> menus = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员权限
        if (user.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            roles = null;
            menus = null;
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入认证对象
            info.setStringPermissions(menus);
        }

        return info;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        SysUser user = null;

        return null;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
