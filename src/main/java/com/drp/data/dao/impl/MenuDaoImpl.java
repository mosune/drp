package com.drp.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.drp.data.entity.Menu;
import com.drp.data.dao.MenuDao;

import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

    @Override
    public List<Menu> getParentMenu() {
        return getSqlSession().selectList(getSqlName("getParentMenu"));
    }

    @Override
    public List<Menu> getAllMenu(Integer id) {
        return getSqlSession().selectList(getSqlName("getAllMenu"), id);
    }
}