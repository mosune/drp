package com.drp.service;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Role;
import com.drp.util.Page;
import com.drp.util.PageParam;

import java.util.List;

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
	 * @param id
	 * @return
	 */
	JSONObject delete(Integer id);

	/**
	 * 修改（按主键）
	 * @param role
	 * @return
	 */
	JSONObject update(Role role);

	/**
	 * 获取（按主键）
	 * @param role
	 * @return
	 */
	Role get(Role role);

	/**
	 * 查询列表
	 * @param pageParam
	 * @return
	 */
    Page<Role> find(PageParam pageParam);

	/**
	 * 添加岗位
	 * @param role
	 * @return
	 */
	JSONObject add(Role role);

	/**
	 * 修改项目状态
	 * @param id
	 * @return
	 */
	JSONObject updateStatus(Integer id);

	/**
	 * 查询列表
	 * @return
	 */
    List<Role> getList();

	/**
	 * 获取角色相关信息
	 * @param id
	 * @return
	 */
	JSONObject getData(Integer id);

    JSONObject savePower(int id, String power);
}