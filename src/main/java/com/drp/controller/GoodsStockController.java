package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.GoodsStock;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drp.service.GoodsStockService;

import java.util.HashMap;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/goodsStock")
public class GoodsStockController extends BaseController {

	@Autowired
	private GoodsStockService goodsStockService;

	@RequestMapping("index.do")
	public String index() {
		return "/stock/index";
	}

	/**
	 * 首页列表
	 * @param limit
	 * @param offset
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.do")
	public JSONObject list(int limit, int offset, String nameLike) {
		JSONObject result = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!UserUtil.getCurUserId().equals("1")) map.put("shop_id", UserUtil.getCurShopId());
		map.put("name", nameLike);
		PageParam pageParam = new PageParam(offset, limit, map);
		Page<GoodsStock> page = goodsStockService.find(pageParam);
		result.put("total", page.getTotal());
		result.put("rows", page.getRows());
		return result;
	}

	/**
	 * 采购单入库
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/purchaseIn.do")
	public JSONObject purchaseIn(String orderId, String type) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(orderId)) {
			result.put("msg", "请选择需要入库的采购单");
			return result;
		}
		return goodsStockService.stock(orderId, type);
	}

	/**
	 * 采购单取消
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete.do")
	public JSONObject delete(String orderId, String type) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(orderId)) {
			result.put("msg", "请选择需要取消的采购单");
			return result;
		}
		return goodsStockService.delete(orderId, type);
	}
}