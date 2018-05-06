package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.GoodsStockLog;
import com.drp.service.GoodsStockLogService;
import com.drp.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:09
 */
@Controller
@RequestMapping("/goodsStockLog")
public class GoodsStockLogController extends BaseController {

	@Autowired
	private GoodsStockLogService goodsStockLogService;

	/**
	 * 首页列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.do")
	public JSONObject list(String goodsId) {
		JSONObject result = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!UserUtil.getCurUserId().equals("1")) map.put("shop_id", UserUtil.getCurShopId());
		map.put("goods_id", goodsId);
		List<GoodsStockLog> list = goodsStockLogService.getList(map);
		result.put("rows", list);
		return result;
	}
	
}