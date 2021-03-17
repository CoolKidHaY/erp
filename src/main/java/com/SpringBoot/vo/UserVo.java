package com.SpringBoot.vo;

import com.SpringBoot.bean.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {
    private Long pageNum;
    private Long pageSize;
    private List<Long> ids;

}