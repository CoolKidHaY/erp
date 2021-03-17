package com.SpringBoot.vo;

import com.SpringBoot.bean.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title: CustomerVo
 * @Author HuangYan
 * @Date: 2021/2/20 9:13
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerVo extends Customer {

    private Integer currentPage;
    private Integer pageSize;
}
