package com.SpringBoot.mapper;

import com.SpringBoot.bean.Sales;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SalesMapper extends BaseMapper<Sales> {

	public List<Sales> select(@Param("customerid") Integer customerid, @Param("goodsid") Integer goodsid, @Param("index") Integer index, @Param("limit") Integer limit);

	public void insert(@Param("customerid") Integer customerid, @Param("paytype") String paytype, @Param("salestime") Date salestime, @Param("operateperson") String operateperson,
                       @Param("number") Integer number, @Param("remark") String remark, @Param("saleprice") Double saleprice, @Param("goodsid") Integer goodsid);

	public void update(@Param("id") Integer id, @Param("customerid") Integer customerid, @Param("paytype") String paytype, @Param("salestime") Date salestime, @Param("operateperson") String operateperson,
                       @Param("number") Integer number, @Param("remark") String remark, @Param("saleprice") Double saleprice, @Param("goodsid") Integer goodsid);

	public void delete(Integer id);

	public Sales selectById(Integer id);

	public void updateNumber(@Param("id") Integer id, @Param("number") Integer number);
}
