package com.SpringBoot.vo;

import lombok.Data;

/**
 * @title: PwdVo
 * @Author HuangYan
 * @Date: 2021/3/12 13:58
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
@Data
public class PwdVo {
    private Long userId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
