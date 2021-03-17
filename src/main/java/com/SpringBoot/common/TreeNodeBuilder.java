package com.SpringBoot.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 把没有层级关系的集合变成有层级关系的集合
 * @Author: 落亦-
 * @Date: 2019/11/22 16:31
 */
public class TreeNodeBuilder {
    public static List<TreeNode> build(List<TreeNode> treeNodes, Long topPid) {
        // 目录集合
        List<TreeNode> mTreeNode = treeNodes.stream().filter(node -> node.getType().equalsIgnoreCase("M")).collect(Collectors.toList());
        // 菜单集合
        List<TreeNode> cTreeNode = treeNodes.stream().filter(node -> node.getType().equalsIgnoreCase("C")).collect(Collectors.toList());
        List<TreeNode> nodes = new ArrayList<TreeNode>();

        for (TreeNode treeNode : mTreeNode) {

            if (treeNode.getChildren() == null) treeNode.setChildren(new ArrayList<>());

            // 根目录
            if (treeNode.getPid() == topPid) nodes.add(treeNode);

            // 为目录添加子菜单
            for (TreeNode node : cTreeNode) {
                if (node.getChildren() == null) node.setChildren(new ArrayList<>());

                if (treeNode.getId().equals(node.getPid())){
                    treeNode.getChildren().add(node);
                }
            }
        }
        return nodes;
    }
}
