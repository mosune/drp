package com.drp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Order;
import com.drp.data.entity.dto.OrderGoodsDto;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drp.service.OrderService;

import java.util.HashMap;
import java.util.List;

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
	 * 跳转退货管理
	 * @return
	 */
	@RequestMapping("/return.do")
	public String returns() {
		return "/return/index";
	}

    /**
     * 跳转退货管理
     * @return
     */
    @RequestMapping("/sale.do")
    public String sale() {
        return "/sale/index";
    }

	@RequestMapping("/ret.do")
	public String ret() {
		return "/ret/index";
	}

	@RequestMapping("/storage.do")
	public String storage() {
		return "/storage/index";
	}

	/**
	 * 首页列表
	 * @param limit
	 * @param offset
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.do")
	public JSONObject list(int limit, int offset, String nameLike, @RequestParam("status[]") Integer[] status) {
		JSONObject result = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>(3);
		if (!UserUtil.getCurUserId().equals("1")) map.put("shop_id", UserUtil.getCurShopId());
		map.put("name", nameLike);
		map.put("status", status);
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
	 *
	 * @return
	 */
	@RequestMapping("addReturn.do")
	public String addReturn() {
		return "/return/add";
	}

    @RequestMapping("addSale.do")
    public String addSale() {
        return "/sale/add";
    }

	@RequestMapping("addRet.do")
	public String addRet() {
		return "/ret/add";
	}

	@RequestMapping("out.do")
	public String out() {
		return "/outs/index";
	}

	/**
	 * 添加采购单
	 * @param order
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addOrder.do")
	public JSONObject addOrder(String order, int type) {
		JSONObject result = new JSONObject();
		JSONArray json = JSONArray.parseArray(order);
		if (json.size() == 0) {
			result.put("msg", "请选择需要采购的货物");
			return result;
		}
		orderService.addOrder(json, type);
		return result;
	}

	/**
	 * 删除订购单
	 * @param id
	 * @return
	 */
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

	/**
	 * 获取订购单相关的货物信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getGoods.do")
	public JSONObject getGoods(String id) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(id)) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		List<OrderGoodsDto> list = orderService.getGoods(id);
		result.put("rows", list);
		return result;
	}

}