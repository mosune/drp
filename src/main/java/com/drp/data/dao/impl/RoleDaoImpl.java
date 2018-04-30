package com.drp.data.dao.impl;

import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.Role;
import com.drp.data.dao.RoleDao;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public Page<Role> find(PageParam pageParam) {
        Page<Role> page = new Page();
        page.setRows(getSqlSession().<Role>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }
}