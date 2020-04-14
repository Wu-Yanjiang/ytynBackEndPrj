package com.wyj.ytyn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyj.ytyn.entity.SysLogininfor;
import com.wyj.ytyn.mapper.SysLogininforMapper;
import com.wyj.ytyn.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor) {

    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
        return null;
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids) {
        return 0;
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {

    }
}
