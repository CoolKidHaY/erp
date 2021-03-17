package com.SpringBoot.mapper;

import com.SpringBoot.bean.LogInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface LogInfoMapper extends BaseMapper<LogInfo> {
}
