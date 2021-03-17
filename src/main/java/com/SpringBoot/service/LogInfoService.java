package com.SpringBoot.service;

import com.SpringBoot.vo.LogInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.SpringBoot.bean.LogInfo;

public interface LogInfoService extends IService<LogInfo> {

    /**
     * 查询日志
     * @param vo
     * @return
     */
    IPage<LogInfo> selectLogInfo(LogInfoVo vo);

    /**
     * id删除记录
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 批量删除
     * @param vo
     * @return
     */
    int batchDeleteLogInfo(LogInfoVo vo);


    int insertLogIfo(LogInfo logInfo);
}
