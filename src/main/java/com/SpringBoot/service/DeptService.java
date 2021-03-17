package com.SpringBoot.service;

import com.SpringBoot.vo.DeptVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.SpringBoot.bean.Dept;

import java.util.Date;
import java.util.List;

public interface DeptService extends IService<Dept> {

    List<Dept> select(Dept dept);

    /**
     * 根据id查询父bumen
     * @param id
     * @return
     */
    Dept getFatherById(Long id);

    /**
     * 新增部门
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 修改部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * id查询部门
     * @param id
     * @return
     */
    Dept selectDeptById(Long id);

    /**
     * id删除部门
     * @param id
     * @return
     */
    int deleteDeptById(Long id);

    /**
     * 查询所有部门
     * @return
     */
    List<Dept> selectAllDept();

}
