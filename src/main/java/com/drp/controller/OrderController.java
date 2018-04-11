package com.drp.controller;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Goods;
import com.drp.data.entity.Order;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drp.service.OrderService;

import java.util.HashMap;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:23
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index() {
		return "/order/index";
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
//		map.put("shop_id", UserUtil.getCurShopId());
//		map.put("name", nameLike);
		PageParam pageParam = new PageParam(offset, limit, map);
		Page<Order> page = orderService.find(pageParam);
		result.put("total", page.getTotal());
		result.put("rows", page.getRows());
		return result;
	}

	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping("addPage.do")
	public String addPage() {
		return "/order/add";
	}

}