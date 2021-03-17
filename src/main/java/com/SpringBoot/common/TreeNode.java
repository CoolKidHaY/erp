package com.SpringBoot.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 落亦-
 * @Date: 2019/11/22 15:25
 */
@Data
public class TreeNode {

    private Long id;
    @JsonProperty("parentId")
    private Long pid;
    private String title;
    private String name;
    private String type;
    private String icon;
    private String url;
    
    private List<TreeNode> children;

    /**
     * 0为不选中  1为选中
     */
    private String checkArr="0";
    private Boolean checked = false;

    /**
     * 首页左边导航菜单的构造器
     * @param id
     * @param pid
     * @param title
     * @param icon
     * @param url
     */
    public TreeNode(Long id, Long pid, String title, String type, String icon, String url) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.type = type;
        this.icon = icon;
        this.url = url;
    }

    /**
     * 部门 dtree的构造器
     * @param id
     * @param pid
     * @param title
     */
    public TreeNode(Long id, Long pid, String title, String type, Boolean checked) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.type = type;
        this.checked = checked;
    }

    public TreeNode(Long id, Long pid, String title, String type) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.type = type;
    }

    /**
     * 给角色分配权限的构造器
     * @param id
     * @param pid
     * @param title
     * @param checkArr
     */
    public TreeNode(Long id, Long pid, String title, String type, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.type = type;
        this.checkArr = checkArr;
    }

    public TreeNode(){};

    public static <T> TreeNode getTreeNode(T node){
        TreeNode treeNode = new TreeNode();
        try {
            CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true);
            BeanUtil.copyProperties(node, treeNode, copyOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeNode;
    }

    public static <T> TreeNode getTreeNode(T node, Boolean checked){
        TreeNode treeNode = new TreeNode();
        try {
            CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true);
            BeanUtil.copyProperties(node, treeNode, copyOptions);
            treeNode.setChecked(checked);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeNode;
    }
}
