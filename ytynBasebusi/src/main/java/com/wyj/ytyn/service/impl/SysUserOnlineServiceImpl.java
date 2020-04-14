package com.wyj.ytyn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyj.ytyn.entity.SysUserOnline;
import com.wyj.ytyn.mapper.SysUserOnlineMapper;
import com.wyj.ytyn.service.ISysUserOnlineService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysUserOnlineServiceImpl extends ServiceImpl<SysUserOnlineMapper, SysUserOnline> implements ISysUserOnlineService {
    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return null;
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public void deleteOnlineById(String sessionId) {

    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    @Override
    public void batchDeleteOnline(List<String> sessions) {

    }

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    @Override
    public void saveOnline(SysUserOnline online) {

    }

    /**
     * 查询会话集合
     *
     * @param userOnline 分页参数
     * @return 会话集合
     */
    @Override
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline) {
        return null;
    }

    /**
     * 强退用户
     *
     * @param sessionId 会话ID
     */
    @Override
    public void forceLogout(String sessionId) {

    }

    /**
     * 查询会话集合
     *
     * @param expiredDate 有效期
     * @return 会话集合
     */
    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        return null;
    }
}
