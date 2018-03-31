package com.drp.service;

import com.drp.data.entity.AdminUser;
import com.drp.util.Page;
import com.drp.util.PageParam;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface AdminUserService {

	/**
	 * 保存
	 * @param adminUser
	 * @return
	 */
	Object save(AdminUser adminUser);
	
	/**
	 * 删除（按主键）
	 * @param adminUser
	 * @return
	 */
	int delete(AdminUser adminUser);

	/**
	 * 修改（按主键）
	 * @param adminUser
	 * @return
	 */
	int update(AdminUser adminUser);

	/**
	 * 获取（按主键）
	 * @param adminUser
	 * @return
	 */
	AdminUser get(AdminUser adminUser);

	/**
	 * 返回分页参数
	 * @param pageParam
	 * @return
	 */
	Page<AdminUser> find(PageParam pageParam);
}