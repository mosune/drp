package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Category;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drp.service.CategoryService;

import java.util.HashMap;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index() {
		return "/category/index";
	}

	/**
	 * 首页列表
	 * @param limit
	 * @param offset
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.do")
	public JSONObject list(int limit, int offset) {
		JSONObject result = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("shop_id", UserUtil.getCurShopId());
		PageParam pageParam = new PageParam(offset, limit, map);
		Page<Category> page = categoryService.find(pageParam);
		result.put("total", page.getTotal());
		result.put("rows", page.getRows());
		return result;
	}

	/**
	 * 添加或者修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addOrUpdate.do")
	public JSONObject addOrUpdate(Category category) {
		return categoryService.addOrUpdate(category);
	}

	/**
	 * 获取第一级列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getTopLevel.do")
	public JSONObject getTopLevel() {
		JSONObject result = new JSONObject();
		result.put("list", categoryService.getTopLevel());
		return result;
	}
	
}