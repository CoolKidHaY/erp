package com.SpringBoot.vo;

import com.SpringBoot.bean.Role;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

/**
 * @title: RoleVo
 * @Author HuangYan
 * @Date: 2021/2/25 15:01
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
@Data
public class RoleVo extends Role {

    private Integer pageNum;

    private Integer pageSize;

    // 角色所有的菜单
    private List<Long> menuIds;

}
