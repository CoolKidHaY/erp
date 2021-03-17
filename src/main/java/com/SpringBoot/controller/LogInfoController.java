package com.SpringBoot.controller;

import com.SpringBoot.service.LogInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.bean.LogInfo;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.vo.LogInfoVo;


@RestController
@RequestMapping("logInfo")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoService;
    
    @Autowired
	LayuiJson layuiJson;

    /**
     * 查询所有登陆日志的信息
     * @param
     * @return
     */
    @RequestMapping("loadAllLogInfo")
    public LayuiJson<LogInfo> loadAllLogInfo(LogInfoVo vo){
    	IPage<LogInfo> page = logInfoService.selectLogInfo(vo);
		return new LayuiJson<>(page.getRecords(), page.getTotal(), 0);
    }

    /**
     * 删除单条日志
     * @param id
     * @return
     */
    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(Long id){
        try {
            int row = logInfoService.deleteById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param vo
     * @return
     */
    @RequestMapping("batchDeleteLogInfo")
    public ResultObj batchDeleteLogInfo(LogInfoVo vo){
        try {
            int row = logInfoService.batchDeleteLogInfo(vo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

