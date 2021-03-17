package com.SpringBoot.vo;

import com.SpringBoot.bean.Menu;

import lombok.Data;

/**
 * @Author:
 * @Date: 2019/11/22 15:30
 */
@Data
public class MenuVo extends Menu {

    private Long pageNum;
    private Long pageSize;
}
