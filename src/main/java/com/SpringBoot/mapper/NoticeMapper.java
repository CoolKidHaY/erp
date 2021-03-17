package com.SpringBoot.mapper;

import com.SpringBoot.bean.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

	public List<Notice> select(@Param("title") String title, @Param("opername") String opername
            , @Param("index") Integer index, @Param("limit") Integer limit);

	public void delect(Integer id);

	public void insert(@Param("title") String title, @Param("content") String content, @Param("createtime") Date createtime, @Param("opername") String opername);

	public void update(@Param("id") Integer id, @Param("content") String content);
	
	public Notice selectById(Integer id);
}
