package com.drp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Goods;
import com.drp.data.entity.Order;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
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

	/**
	 * 添加采购单
	 * @param order
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addOrder.do")
	public JSONObject addOrder(String order) {
		JSONObject result = new JSONObject();
		JSONArray json = JSONArray.parseArray(order);
		if (json.size() == 0 ) {
			result.put("msg", "请选择需要采购的货物");
			return result;
		}
		orderService.addOrder(json);
		return null;
	}

	@ResponseBody
	@RequestMapping("delete.do")
	public JSONObject delete(String id) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(id)) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		return orderService.delete(id);
	}

}