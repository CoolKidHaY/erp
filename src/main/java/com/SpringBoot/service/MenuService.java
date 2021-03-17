package com.SpringBoot.service;

import com.SpringBoot.bean.User;
import com.SpringBoot.vo.MenuVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.SpringBoot.bean.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService extends IService<Menu> {

    List<Menu> selectMenu();

    int addMenu(Menu menu, User user);

    Set<String> selectPerCodesByUserId(Long id);

    List<Menu> selectRoleHasMenus(Long id);

    List<Menu> selectUserHasMenus(Long id);

    Set<String> selectRolesByUid(Long id);

    /**
     * 查询菜单是否有子菜单
     * @param id
     * @return
     */
    int selectMenuSonById(Long id);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    int deleteMenuById(Long id);

    /**
     * 名称查询目录
     * @param vo
     * @return
     */
    List<Menu> selectMenuByTitle(MenuVo vo);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);
}
