package com.wyj.ytyn.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.subject.WebSubject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

/**
 * 域配置
 */

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("开始授权");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils.getSubject()).getServletRequest(); // 可以获取登录时获取的其他参数信息
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 受理权限
        // 角色
        HashSet<String> roles = new HashSet<>();
        roles.add("role1");
        authorizationInfo.setRoles(roles);

        // 权限
        HashSet<String> permissions = new HashSet<>();
        permissions.add("user:list");
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     * 设置认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("开始认证");
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils.getSubject()).getServletRequest();
        UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("userName"), request.getParameter("password"));

        String username = token.getUsername();
        // 通过username做数据库匹配，再根据情况做处理
        log.info("账号:" + username);

        if ("passwordError".equals(username)) {
            throw new IncorrectCredentialsException("账号或密码错误");
        } else if ("lockedAccount".equals(username)) {
            throw new LockedAccountException("用户被冻结");
        } else {
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    username,
                    token.getPassword(),
                    ByteSource.Util.bytes(username + "salt"),
                    getName()
            );

            return authenticationInfo;
        }
    }
}
