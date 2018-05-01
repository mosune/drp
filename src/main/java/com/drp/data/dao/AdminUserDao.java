package com.drp.data.dao;

import com.drp.data.entity.AdminUser;
import com.drp.util.Page;
import com.drp.util.PageParam;

import java.util.Set;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface AdminUserDao extends BaseDao<AdminUser> {

    /**
     * 获取分页数据
     * @param pageParam
     * @return
     */
    Page<AdminUser> find(PageParam pageParam);

    Set<String> findRoleName(Integer id);
}