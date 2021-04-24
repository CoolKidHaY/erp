package com.SpringBoot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.SpringBoot.bean.Dept;
import com.SpringBoot.bean.User;
import com.SpringBoot.service.MenuService;
import com.SpringBoot.service.impl.MenuServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.SpringBoot.bean.Menu;
import com.SpringBoot.common.Constast;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.common.TreeNode;
import com.SpringBoot.common.TreeNodeBuilder;
import com.SpringBoot.vo.MenuVo;


@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    private final String prefix = "system/menu";

    @RequestMapping("/loadIndexLeftMenuJson")
    @ResponseBody
    public DataGridView loadIndexLeftMenuJson(MenuVo menuVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //查询所有菜单
        List<Menu> list = null;
        if (user.getType().equals(Constast.USER_TYPE_SUPER)) {
            //用户类型为超级管理员
            list = menuService.selectMenu();
        } else {
            list = menuService.selectUserHasMenus(user.getId());
        }

        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //将权限放入treeNodes中，组装成json
        for (Menu menu : list) {
            treeNodes.add(TreeNode.getTreeNode(menu));
        }

        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1l);

        //构造层级关系

        return new DataGridView(list2);

    }


    /************************菜单管理*********************************/

    /**
     * 加载菜单左边的菜单树
     *
     * @param
     * @return
     */
    @RequestMapping("/loadMenuManagerLeftTreeJson")
    @ResponseBody
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //查询出所有的菜单，存放进list中
        List<Menu> list = menuService.selectUserHasMenus(user.getId());
        List<TreeNode> treeNodes = new ArrayList<>();
        //将菜单放入treeNodes中，组装成json
        for (Menu menu : list) {
            treeNodes.add(TreeNode.getTreeNode(menu));
        }
        return new DataGridView(treeNodes);
    }

    @GetMapping("/menuAllTree")
    @ResponseBody
    public List<TreeNode> getAllMenuTree(Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //查询出此登陆用户所拥有的菜单，存放进list中
        List<Menu> userHasMenuList = menuService.selectUserHasMenus(user.getId());

        // 查询出此角色已有权限的菜单
        List<Menu> roleHasMenuList = menuService.selectRoleHasMenus(id);
        List<TreeNode> treeNodes = new ArrayList<>();

        //将菜单放入treeNodes中，组装成json
        for (Menu menu : userHasMenuList) {

            boolean contains = roleHasMenuList.contains(menu);

            treeNodes.add(TreeNode.getTreeNode(menu, contains));
        }
        return treeNodes;
    }

    /**
     * 根据登陆用户id查询此用户拥有的权限菜单
     *
     * @param id
     * @return
     */
    @GetMapping("/selectHasMenuByUserId/{id}")
    @ResponseBody
    public List<TreeNode> selectHasMenuByUserId(@PathVariable("id") Long id) {
        List<Menu> userHasMenuList = menuService.selectUserHasMenus(id);
        List<TreeNode> treeNodes = new ArrayList<>();
        //将菜单放入treeNodes中，组装成json
        for (Menu menu : userHasMenuList) {
            treeNodes.add(TreeNode.getTreeNode(menu, false));
        }
        return treeNodes;
    }

    /**
     * 查询所有菜单数据
     *
     * @param
     * @return
     */
    @RequestMapping("/loadAllMenu")
    @ResponseBody
    public LayuiJson<Menu> selectMenuByTitle(MenuVo vo) {
        List<Menu> data= menuService.selectMenuByTitle(vo);
        LayuiJson<Menu> resultData = new LayuiJson<>(data, (long) data.size(), 0);
        return resultData;
    }

    @GetMapping("/addMenuIndex")
    public String addMenuIndex() {
        return prefix + "/add";
    }

    /**
     * 添加菜单
     *
     * @param
     * @return
     */
    @RequestMapping("/addMenu")
    @ResponseBody
    public ResultObj addMenu(Menu menu, HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            //设置添加类型为 menu
            menuService.addMenu(menu, user);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新菜单
     *
     * @param
     * @return
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public ResultObj updateMenu(Menu menu) {
        try {
            int row = menuService.updateMenu(menu);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前菜单是否有子菜单
     *
     * @param
     * @return
     */
    @RequestMapping("/checkMenuHasChildrenNode")
    @ResponseBody
    public Map<String, Boolean> checkMenuHasChildrenNode(Long id) {
        Map<String, Boolean> map = new HashMap<>();
        int count = menuService.selectMenuSonById(id);
        if (count > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除菜单
     *
     * @param
     * @return
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public ResultObj deleteMenu(Long id) {
        int row = menuService.deleteMenuById(id);
        return row > 0 ? ResultObj.DELETE_SUCCESS : ResultObj.DELETE_ERROR;
    }


}
