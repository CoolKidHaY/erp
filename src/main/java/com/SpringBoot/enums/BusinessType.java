package com.SpringBoot.enums;

/**
 * 功能枚举类
 *
 */
public enum BusinessType {

    /**
     * 其他
     */
    OTHER ("其他"),
    /**
     * 新增
     */
    INSERT ("新增"),
    /**
     * 修改
     */
    UPDATE("修改"),
    /**
     * 删除
     */
    REMOVE("删除"),

    EXPORT("导出");
    private final String disc;

    BusinessType(String disc) {
        this.disc = disc;
    }

    public String getDisc() {
        return disc;
    }
}
