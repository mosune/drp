package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.GoodsStock;
import com.drp.data.entity.GoodsStockLog;
import com.drp.data.entity.dto.GoodsStockDto;
import com.drp.service.GoodsStockLogService;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drp.service.GoodsStockService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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

	@Autowired
	private GoodsStockLogService goodsStockLogService;

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
		map.put("shop_id", UserUtil.getCurShopId());
		map.put("name", nameLike);
		PageParam pageParam = new PageParam(offset, limit, map);
		Page<GoodsStockDto> page = goodsStockService.find(pageParam);
		for (GoodsStockDto goodsStock : page.getRows()) {
			BigDecimal price = new BigDecimal(0);
			map.put("shop_id", UserUtil.getCurShopId());
			map.put("goods_id", goodsStock.getGoodsId());
			List<GoodsStockLog> list = goodsStockLogService.getList(map);
			for (GoodsStockLog goodsStockLog : list) {
				if (goodsStockLog.getType().equals("in")) {
					price = price.subtract(goodsStock.getOriginalPrice().multiply(new BigDecimal(goodsStockLog.getQuantity())));
				} else if (goodsStockLog.getType().equals("back_in")) {
					price = price.subtract(goodsStock.getSalePrice().multiply(new BigDecimal(goodsStockLog.getQuantity())));
				} else if (goodsStockLog.getType().equals("out")) {
					price = price.add(goodsStock.getOriginalPrice().multiply(new BigDecimal(goodsStockLog.getQuantity())));
				} else {
					price = price.add(goodsStock.getSalePrice().multiply(new BigDecimal(goodsStockLog.getQuantity())));
				}
				goodsStock.setPrice(price);
			}
		}
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
		if (StringUtils.isEmpty(type)) {
			result.put("msg", "数据错误，请刷新重试");
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