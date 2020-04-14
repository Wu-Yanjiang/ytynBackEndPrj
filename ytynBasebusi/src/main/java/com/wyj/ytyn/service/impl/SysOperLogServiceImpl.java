package com.wyj.ytyn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyj.ytyn.entity.SysOperLog;
import com.wyj.ytyn.mapper.SysOperLogMapper;
import com.wyj.ytyn.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {

    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return null;
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(String ids) {
        return 0;
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return null;
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {

    }
}
