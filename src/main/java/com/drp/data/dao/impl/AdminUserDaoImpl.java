package com.drp.data.dao.impl;

import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.AdminUser;
import com.drp.data.dao.AdminUserDao;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("adminUserDao")
public class AdminUserDaoImpl extends BaseDaoImpl<AdminUser> implements AdminUserDao {

    @Override
    public Page<AdminUser> find(PageParam pageParam) {
        Page<AdminUser> page = new Page();
        page.setRows(getSqlSession().<AdminUser>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }

    @Override
    public Set<String> findRoleName(Integer id) {
        String name = getSqlSession().selectOne(getSqlName("findRoleName"), id);
        Set<String> set = new HashSet<String>();
        set.add(name);
        return set;
    }
}