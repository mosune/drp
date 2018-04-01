package com.drp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Category;
import com.drp.data.dao.CategoryDao;
import com.drp.service.CategoryService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

	@Override
	public Page<Category> find(PageParam pageParam) {
		return categoryDao.find(pageParam);
	}

	@Override
	public JSONObject addOrUpdate(Category category) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(category.getName())) {
			result.put("msg", "名称不得为空");
			return result;
		}
		if (category.getLevel() == null) {
			result.put("msg", "级别不得为空");
			return result;
		}
		if (category.getId() == null) {
			Category newCate = new Category();
			newCate.setCreateBy(UserUtil.getCurUserId());
			newCate.setCreateTime(new Date());
			if (StringUtils.isNotBlank(category.getRemark())) newCate.setRemark(category.getRemark());
			newCate.setLevel(category.getLevel());
			newCate.setName(category.getName());
			newCate.setShopId(UserUtil.getCurShopId());
			newCate.setStatus(1);
			newCate.setUpdateBy(UserUtil.getCurUserId());
			newCate.setUpdateTime(new Date());
			categoryDao.insert(newCate);
		} else {
			Category newCate = new Category(category.getId());
			newCate = categoryDao.get(newCate);
			if (!newCate.getName().equals(category.getName())) newCate.setName(category.getName());
			if (!newCate.getLevel().equals(category.getLevel())) newCate.setLevel(category.getLevel());
			if (!newCate.getRemark().equals(category.getRemark())) newCate.setRemark(category.getRemark());
			newCate.setUpdateBy(UserUtil.getCurUserId());
			newCate.setUpdateTime(new Date());
			categoryDao.update(newCate);
		}
		return result;
	}

	@Override
	public List<Category> getTopLevel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_id", UserUtil.getCurShopId());
		map.put("level", 0);
		map.put("status", 1);
		return categoryDao.getTopLevel(map);
	}

}