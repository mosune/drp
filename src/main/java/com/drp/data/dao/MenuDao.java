package com.drp.data.dao;

import com.drp.data.entity.Menu;

import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface MenuDao extends BaseDao<Menu> {

    /**
     * 获取父级菜单
     * @return
     */
    List<Menu> getParentMenu();

    /**
     * 获取所有菜单
     * @param id
     * @return
     */
    List<Menu> getAllMenu(Integer id);
}