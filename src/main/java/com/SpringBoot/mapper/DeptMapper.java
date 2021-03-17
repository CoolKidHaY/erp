package com.SpringBoot.mapper;

import com.SpringBoot.bean.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;


@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

}
