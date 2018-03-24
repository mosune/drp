package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.Role;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface RoleService {

	/**
	 * 保存
	 * @param role
	 * @return
	 */
	Object save(Role role);
	
	/**
	 * 删除（按主键）
	 * @param role
	 * @return
	 */
	int delete(Role role);

	/**
	 * 修改（按主键）
	 * @param role
	 * @return
	 */
	int update(Role role);

	/**
	 * 获取（按主键）
	 * @param role
	 * @return
	 */
	Role get(Role role);

}