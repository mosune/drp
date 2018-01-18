package com.drp.data.dao.impl;

import com.drp.data.dao.RoleDao;
import com.drp.data.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FileName: RoleDaoImpl
 *
 * @author gcg
 * @create 2018/01/03 10:05
 * Description: role dao impl
 * History:
 **/
@Repository(value = "roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public List<Integer> getRoleIdByUserName(String userName) {
        return this.getSqlSession().selectList(getSqlName("getRoleIdByUserName"), userName);
    }

    @Override
    public List<Object> getPerByRoleIds(List<Integer> roleIds) {
        return this.getSqlSession().selectList(getSqlName("getPerByRoleIds"), roleIds);
    }

}
