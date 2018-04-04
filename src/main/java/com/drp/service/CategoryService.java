package com.drp.service;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Category;
import com.drp.util.Page;
import com.drp.util.PageParam;

import java.util.List;

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
	JSONObject delete(Category category);

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

	/**
	 * 获取首页列表
	 * @param pageParam
	 * @return
	 */
	Page<Category> find(PageParam pageParam);

	/**
	 * 添加或者删除类别
	 * @param category
	 * @return
	 */
    JSONObject addOrUpdate(Category category);

	/**
	 * 获取第一级别的列表
	 * @return
	 */
	List<Category> getTopLevel();

	/**
	 * 修改状态
	 * @param category
	 * @return
	 */
	JSONObject updateStatus(Category category);

	/**
	 * 获取二级列表
	 * @return
	 */
	Object getSecondLevel();
}