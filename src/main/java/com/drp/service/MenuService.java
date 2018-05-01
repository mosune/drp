package com.drp.service;

import com.drp.data.entity.Menu;

import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface MenuService {

	/**
	 * 保存
	 * @param menu
	 * @return
	 */
	Object save(Menu menu);
	
	/**
	 * 删除（按主键）
	 * @param menu
	 * @return
	 */
	int delete(Menu menu);

	/**
	 * 修改（按主键）
	 * @param menu
	 * @return
	 */
	int update(Menu menu);

	/**
	 * 获取（按主键）
	 * @param menu
	 * @return
	 */
	Menu get(Menu menu);

	/**
	 * 获取父级菜单
	 * @return
	 */
	List<Menu> getParentList();

}