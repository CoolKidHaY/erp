package com.SpringBoot.service.impl;

import com.SpringBoot.bean.Dept;
import com.SpringBoot.mapper.DeptMapper;
import com.SpringBoot.service.DeptService;
import com.SpringBoot.vo.DeptVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

	@Override
	public List<Dept> select(Dept dept) {
		QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>().eq(dept.getId() != null, "id", dept.getId())
				.like(dept.getName() != null, "name", dept.getName())
				.like(dept.getRemark() != null, "remark", dept.getRemark())
				.like(dept.getAddress() != null, "address", dept.getAddress())
				.eq("delete_flag", 0)
				.orderByAsc("order_num");
		return baseMapper.selectList(queryWrapper);
	}

	@Override
	public Dept getFatherById(Long id) {
		return this.getBaseMapper().selectOne(new QueryWrapper<Dept>().eq("id", id));
	}

	/**
	 * 新增部门
	 * @param dept
	 * @return
	 */
	@Override
	public int addDept(Dept dept) {
		dept.setCreateTime(LocalDateTime.now());
		return this.getBaseMapper().insert(dept);
	}

	/**
	 * 修改部门
	 * @param dept
	 * @return
	 */
	@Override
	public int updateDept(Dept dept) {
		return this.getBaseMapper().updateById(dept);
	}

	/**
	 * id查询部门
	 * @param id
	 * @return
	 */
	@Override
	public Dept selectDeptById(Long id) {
		return this.getBaseMapper().selectById(id);
	}

	@Override
	public int deleteDeptById(Long id) {
		return this.baseMapper.deleteById(id);
	}

	@Override
	public List<Dept> selectAllDept() {
		return this.baseMapper.selectList(null);
	}

}
