package com.wyj.ytyn.service.impl;

import com.wyj.ytyn.common.constant.Constants;
import com.wyj.ytyn.common.constant.ShiroConstant;
import com.wyj.ytyn.common.exception.user.UserPasswordNotMatchException;
import com.wyj.ytyn.common.exception.user.UserPasswordRetryLimitExceedException;
import com.wyj.ytyn.entity.SysUser;
import com.wyj.ytyn.manager.AsyncManager;
import com.wyj.ytyn.manager.factory.AsyncFactory;
import com.wyj.ytyn.utils.MessageUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

;

/**
 * 登录密码方法
 */
@Component
public class SysPasswordService {

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstant.LOGINRECORDCACHE);
    }

    public void validate(SysUser user, String password) {
        String loginName = user.getLoginName();
        AtomicInteger retryCount = loginRecordCache.get(loginName);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.parseInt(maxRetryCount)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new UserPasswordRetryLimitExceedException(Integer.parseInt(maxRetryCount));
        }
        if (!matches(user, password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex();
    }

    public void unlock(String loginName){
        loginRecordCache.remove(loginName);
    }

}
