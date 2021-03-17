package com.SpringBoot.mapper;


import com.SpringBoot.bean.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

	public List<Goods> select(@Param("providerid") Integer providerid, @Param("goodsname") String goodsname, @Param("productcode") String productcode,
							  @Param("promitcode") String promitcode, @Param("description") String description, @Param("size") String size, @Param("index") Integer index, @Param("limit") Integer limit);
	
	public void insert(@Param("providerid") Integer providerid, @Param("goodsname") String goodsname, @Param("productcode") String productcode,
                       @Param("promitcode") String promitcode, @Param("description") String description, @Param("size") String size, @Param("produceplace") String produceplace,
                       @Param("goodspackage") String goodspackage, @Param("price") Double price, @Param("number") Integer number, @Param("dangernum") Integer dangernum, @Param("available") Integer available);
	
	public void delete(Integer id);
	
	public void update(@Param("id") Integer id, @Param("providerid") Integer providerid, @Param("goodsname") String goodsname, @Param("productcode") String productcode,
                       @Param("promitcode") String promitcode, @Param("description") String description, @Param("size") String size, @Param("produceplace") String produceplace,
                       @Param("goodspackage") String goodspackage, @Param("price") Double price, @Param("number") Integer number, @Param("dangernum") Integer dangernum, @Param("available") Integer available);
	
	public List<Goods> selectGoodsName();
	
	public void updateNumber(@Param("id") Integer id, @Param("number") Integer number);
	
	public List<Goods> selectByProviderid(Integer providerid);
	
	public Goods selectById(Integer id);
	
}
