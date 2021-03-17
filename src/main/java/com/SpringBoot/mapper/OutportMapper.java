package com.SpringBoot.mapper;

import com.SpringBoot.bean.Outport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OutportMapper extends BaseMapper<Outport> {

	public List<Outport> select(@Param("providerid") Integer providerid, @Param("goodsid") Integer goodsid, @Param("index") Integer index, @Param("limit") Integer limit);

	public void insert(@Param("paytype") String paytype, @Param("outputtime") Date outputtime, @Param("operateperson") String operateperson, @Param("number") Integer number, @Param("remark") String remark
            , @Param("outportprice") Double outportprice, @Param("providerid") Integer providerid, @Param("goodsid") Integer goodsid);
	
	public void delete(Integer id);
	
	public Outport selectID(Integer id);
}
