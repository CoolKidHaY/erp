package com.SpringBoot.utils;

/**
 * @title: InStockUtils
 * @Author HuangYan
 * @Date: 2021/5/13 22:55
 * @Version 1.0
 * @Description: ADD YOUR DESCRIPTION
 */
public class InStockUtils {

    public static String returnMaterialStockCapacity(Long num){
        if (num <= 0){
            return "无货";
        } else if (num <= 1000){
            return "紧缺";
        } else if (num <= 5000){
            return "正常";
        } else {
            return "充裕";
        }
    }

    public static String returnProduceStockCapacity(Long num){
        if (num <= 0){
            return "无货";
        } else if (num <= 100){
            return "紧缺";
        } else if (num <= 500){
            return "正常";
        } else {
            return "充裕";
        }
    }
}
