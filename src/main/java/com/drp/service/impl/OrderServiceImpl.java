package com.drp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.GoodsDao;
import com.drp.data.dao.OrderGoodsDao;
import com.drp.data.entity.Goods;
import com.drp.data.entity.OrderGoods;
import com.drp.util.IDUtils;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Order;
import com.drp.data.dao.OrderDao;
import com.drp.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private OrderGoodsDao orderGoodsDao;

	@Override
	public Object save(Order order) {
		return this.orderDao.insert(order);
	}

	@Override
	public int delete(Order order) {
		return this.orderDao.delete(order);
	}

	@Override
	public int update(Order order) {
		return this.orderDao.update(order);
	}

	@Override
	public Order get(Order order) {
		return this.orderDao.get(order);
	}

	@Override
	public Page<Order> find(PageParam pageParam) {
		return this.orderDao.find(pageParam);
	}

	@Override
	@Transactional
	public JSONObject addOrder(JSONArray json) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String num = formatter.format(new Date());
		Order order = new Order();
		order.setId(IDUtils.getUUID());
		order.setUserId(UserUtil.getCurUserId());
		order.setShopId(UserUtil.getCurShopId());
		order.setNumber(num);
		order.setStatus(1);
		order.setCreateTime(new Date());
		order.setCreateBy(UserUtil.getCurUserId());
		order.setUpdateBy(UserUtil.getCurUserId());
		order.setUpdateTime(new Date());
		Object[] objects = json.toArray();
		BigDecimal totalPrice = new BigDecimal(0);
		for (Object object : objects) {
			JSONObject jsonObj = (JSONObject) object;
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setId(IDUtils.getUUID());
			orderGoods.setOrderId(order.getId());
			orderGoods.setGoodsId(Integer.valueOf((String) jsonObj.get("key")));
			orderGoods.setNum(Integer.valueOf((String) jsonObj.get("value")));
			Goods goods = new Goods(Integer.valueOf((String) jsonObj.get("key")));
			goods = goodsDao.get(goods);
			totalPrice = totalPrice.add(goods.getOriginalPrice().multiply(new BigDecimal((String) jsonObj.get("value"))));
			orderGoods.setCreateTime(new Date());
			orderGoods.setCreateBy(IDUtils.getUUID());
			orderGoods.setUpdateBy(IDUtils.getUUID());
			orderGoods.setUpdateTime(new Date());
			orderGoodsDao.insert(orderGoods);
		}
		order.setTotalPrice(totalPrice);
		orderDao.insert(order);
		return null;
	}

	@Override
	public JSONObject delete(String id) {
		JSONObject result = new JSONObject();
		Order order = new Order(id);
		order = orderDao.get(order);
		if (order == null) {
			result.put("msg", "数据错误，请刷新重试");
			return result;
		}
		if (!(order.getStatus() == 1 || order.getStatus() == 11)) {
			result.put("msg", "该状态项目不可修改");
			return result;
		}
		orderDao.delete(order);
		orderGoodsDao.deleteByOrderId(order.getId());
		return result;
	}

}