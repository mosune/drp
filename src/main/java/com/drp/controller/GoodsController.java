package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Goods;
import com.drp.data.entity.GoodsStock;
import com.drp.service.GoodsStockService;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drp.service.GoodsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsStockService goodsStockService;
	
	@RequestMapping("/index.do")
	public String index() {
		return "/goods/index";
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
		Page<Goods> page = goodsService.find(pageParam);
		result.put("total", page.getTotal());
		result.put("rows", page.getRows());
		return result;
	}

	/**
	 * 删除或者修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addOrUpdate.do")
	public JSONObject addOrUpdate(Goods goods, String originalAmount) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(goods.getName())) {
			result.put("msg", "名称不能为空");
			return result;
		}
		if (StringUtils.isEmpty(goods.getCateId())) {
			result.put("msg", "请选择类目");
			return result;
		}
		if (goods.getOriginalPrice() == null) {
			result.put("msg", "成本价不能为空");
			return result;
		}
		if (goods.getSalePrice() == null) {
			result.put("msg", "售卖价不能为空");
			return result;
		}
		return goodsService.addOrUpdate(goods, originalAmount);
	}

	/**
	 * 获取数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getData.do")
	public JSONObject getData(Goods goods) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(goods.getId())) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		return goodsService.getdata(goods);
	}

	/**
	 * 删除书本
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete.do")
	public JSONObject delete(Goods goods) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(goods.getId())) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		return goodsService.deleteLoft(goods);
	}

	/**
	 * 修改状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateStatus.do")
	public JSONObject updateStatus(Goods goods) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(goods.getId())) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		return goodsService.updateStatus(goods);
	}

	/**
	 * 获取货物列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getGoodsList.do")
	public JSONObject getGoodsList() {
		JSONObject result = new JSONObject();
		result.put("list", goodsService.getGoodsList());
		return result;
	}
	
}