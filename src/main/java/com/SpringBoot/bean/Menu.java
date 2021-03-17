package com.SpringBoot.bean;

import java.time.LocalDateTime;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * InnoDB free: 9216 kB
 * </p>
 *
 * @author luoyi-
 * @since 2019-11-22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long pid;

    private String type;

    private String title;

    /**
     * 权限编码[只有type=permission才有 user:view]
     */
    private String perCode;

    private String icon;

    @TableField(value = "url")
    private String url;

    private String target;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Integer orderNum;

    /**
     * 是否隐藏[0不隐藏，1隐藏]
     */
    private Integer visible;

    @TableField(exist = false)
    private String ptitle;

    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu that = (Menu) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
