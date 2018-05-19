package com.drp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.GoodsDao;
import com.drp.data.entity.Goods;
import com.drp.util.IDUtils;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.collections.CollectionUtils;
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

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public Object save(Category category) {
		return this.categoryDao.insert(category);
	}

	@Override
	public JSONObject delete(Category category) {
		JSONObject result = new JSONObject();
		category = categoryDao.get(category);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cate_id", category.getId());
		List<Goods> list = goodsDao.getList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			result.put("msg", "该类目还有商品，不得删除");
			return result;
		}
		this.categoryDao.delete(category);
		return result;
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
		if (category.getId() == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shop_id", UserUtil.getCurShopId());
			map.put("name", category.getName());
			List<Category> list = categoryDao.getList(map);
			if(CollectionUtils.isNotEmpty(list)) {
				result.put("msg", "该类目已存在");
				return result;
			}
			Category newCate = new Category();
			newCate.setCreateBy(UserUtil.getCurUserId());
			newCate.setCreateTime(new Date());
			if (StringUtils.isNotBlank(category.getRemark())) newCate.setRemark(category.getRemark());
			newCate.setName(category.getName());
			newCate.setShopId(UserUtil.getCurShopId());
			newCate.setStatus(1);
			newCate.setUpdateBy(UserUtil.getCurUserId());
			newCate.setUpdateTime(new Date());
			categoryDao.insert(newCate);
		} else {
			Category newCate = new Category(category.getId());
			newCate = categoryDao.get(newCate);
			if (!newCate.getName().equals(category.getName())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shop_id", UserUtil.getCurShopId());
				map.put("name", category.getName());
				List<Category> list = categoryDao.getList(map);
				if(CollectionUtils.isNotEmpty(list)) {
					result.put("msg", "该类目已存在");
					return result;
				}
			}
			newCate.setName(category.getName());
			if (StringUtils.isNotBlank(category.getRemark())) newCate.setRemark(category.getRemark());
			newCate.setUpdateBy(UserUtil.getCurUserId());
			newCate.setUpdateTime(new Date());
			categoryDao.update(newCate);
		}
		return result;
	}

	@Override
	public JSONObject updateStatus(Category category) {
		JSONObject result = new JSONObject();
		Map<String, Object> map =  new HashMap<String, Object>();
		category = categoryDao.get(category);
		if (category.getStatus() == 1) category.setStatus(0);
		else category.setStatus(1);
		categoryDao.update(category);
		return result;
	}

	@Override
	public Object getSecondLevel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_id", UserUtil.getCurShopId());
		map.put("status", 1);
		return categoryDao.getList(map);
	}

}