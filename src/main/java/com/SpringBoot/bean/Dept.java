package com.SpringBoot.bean;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("sys_dept")
public class Dept {

    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long pid;

    private String name;

    /**
     * 是否展开，0不展开，1展开
     */
    private Integer open;

    private String remark;

    private String address;

    /**
     * 是否可用，0不可用，1可用
     */
    private Integer available;

    /**
     * 排序码
     */
    private Integer orderNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String pName;

    @TableLogic(value = "0", delval = "1")
    private Integer delete_flag;

}
