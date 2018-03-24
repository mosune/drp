package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Category;
import com.drp.data.dao.CategoryDao;
import com.drp.service.CategoryService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Object save(Category category) {
		return this.categoryDao.insert(category);
	}

	@Override
	public int delete(Category category) {
		return this.categoryDao.delete(category);
	}

	@Override
	public int update(Category category) {
		return this.categoryDao.update(category);
	}

	@Override
	public Category get(Category category) {
		return this.categoryDao.get(category);
	}
	
}