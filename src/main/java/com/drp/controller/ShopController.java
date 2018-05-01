package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Shop;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drp.service.ShopService;

import java.util.HashMap;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;
	
	@RequestMapping("/index.do")
	public String index() {
		return "/shop/index";
	}

	/**
	 * 首页列表
	 * @param limit
	 * @param offset
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("list.do")
	public JSONObject list(int limit, int offset, String nameLike) {
		JSONObject result = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", nameLike);
		PageParam pageParam = new PageParam(offset, limit, map);
		Page<Shop> page = shopService.find(pageParam);
		result.put("total", page.getTotal());
		result.put("rows", page.getRows());
		return result;
	}

	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("add.do")
	public JSONObject add(Shop shop) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(shop.getName())) {
			result.put("msg", "名称不能为空");
			return result;
		}
		return shopService.add(shop);
	}

	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("update.do")
	public JSONObject update(Shop shop) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(shop.getName())) {
			result.put("msg", "名称不能为空");
			return result;
		}
		return shopService.update(shop);
	}

	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("delete.do")
	public JSONObject delete(Integer shopNum) {
		JSONObject result = new JSONObject();
		if (shopNum == null) {
			result.put("msg", "名称不能为空");
			return result;
		}
		return shopService.delete(shopNum);
	}

	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("getData.do")
	public JSONObject getData(Integer shopNum) {
		JSONObject result = new JSONObject();
		if (shopNum == null) {
			result.put("msg", "名称不能为空");
			return result;
		}
		Shop shop = new Shop(shopNum);
		shop = shopService.get(shop);
		result.put("shop", shop);
		return result;
	}

	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("updateStatus.do")
	public JSONObject updateStatus(Integer shopNum) {
		JSONObject result = new JSONObject();
		if (shopNum == null) {
			result.put("msg", "名称不能为空");
			return result;
		}
		return shopService.updateStatus(shopNum);
	}

	/**
	 * 查询门店列表
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("getList.do")
	public JSONObject getList() {
		JSONObject result = new JSONObject();
		result.put("list", shopService.getList());
		return result;
	}
	
}