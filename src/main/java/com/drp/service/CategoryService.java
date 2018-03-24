package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.Category;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface CategoryService {

	/**
	 * 保存
	 * @param category
	 * @return
	 */
	Object save(Category category);
	
	/**
	 * 删除（按主键）
	 * @param category
	 * @return
	 */
	int delete(Category category);

	/**
	 * 修改（按主键）
	 * @param category
	 * @return
	 */
	int update(Category category);

	/**
	 * 获取（按主键）
			* @param category
	 * @return
			 */
	Category get(Category category);

}