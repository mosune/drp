package com.drp.data.dao;

import java.util.List;

/**
 * FileName: RoleDao
 *
 * @author gcg
 * @create 2018/01/03 10:04
 * Description:
 * History:
 **/
public interface RoleDao {

    /**
     * 通过用户名查询职位id
     * @param userName 用户名
     * @return 职位id列表
     */
    List<Integer> getRoleIdByUserName(String userName);

    /**
     * 通过职位id列表查询权限列表
     * @param roleIds 职位id列表
     * @return 权限列表
     */
    List<Object> getPerByRoleIds(List<Integer> roleIds);
}
