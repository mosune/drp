package com.drp.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.dao.*;
import com.drp.data.entity.*;
import com.drp.util.Page;
import com.drp.util.PageParam;
import com.drp.util.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.service.GoodsStockService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("goodsStockService")
public class GoodsStockServiceImpl implements GoodsStockService {

	@Autowired
	private GoodsStockDao goodsStockDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GoodsStockLogDao goodsStockLogDao;

	@Override
	public Object save(GoodsStock goodsStock) {
		return this.goodsStockDao.insert(goodsStock);
	}

	@Override
	public int delete(GoodsStock goodsStock) {
		return this.goodsStockDao.delete(goodsStock);
	}

	@Override
	public int update(GoodsStock goodsStock) {
		return this.goodsStockDao.update(goodsStock);
	}

	@Override
	public GoodsStock get(GoodsStock goodsStock) {
		return this.goodsStockDao.get(goodsStock);
	}

	@Override
	public Page<GoodsStock> find(PageParam pageParam) {
		return this.goodsStockDao.find(pageParam);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject stock(String orderId,String type) {
		JSONObject result = new JSONObject();
		Order order = new Order(orderId);
		order = orderDao.get(order);
		if (order == null) {
			result.put("msg", "该采购订单不存在");
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_id", orderId);
		List<OrderGoods> orderGoodsList = orderGoodsDao.getList(map);
		if (CollectionUtils.isEmpty(orderGoodsList)) {
			result.put("msg", "该采购订单商品不存在");
			return result;
		}
		for (OrderGoods orderGoods : orderGoodsList) {
			Goods goods = new Goods(orderGoods.getGoodsId());
			goods = goodsDao.get(goods);
			if (goods == null) {
				result.put("msg", "该商品不存在");
				return result;
			}
			map.clear();
			map.put("goods_id", goods.getId());
			List<GoodsStock> goodsStocks = goodsStockDao.getList(map);
			if (CollectionUtils.isEmpty(goodsStocks)) {
				GoodsStock goodsStock = new GoodsStock();
				goodsStock.setId(UUID.randomUUID().toString());
				goodsStock.setShopId(UserUtil.getCurShopId());
				goodsStock.setGoodsId(goods.getId());
				goodsStock.setOriginalStock(0);
				goodsStock.setOutQuentity(0);
				if (type.indexOf("in") != -1) {
					goodsStock.setInQuantity(orderGoods.getNum());
					goodsStock.setCurrentStock(orderGoods.getNum());
				} else {
					result.put("msg", "该商品库存不足，无法出库！");
					return result;
				}
				goodsStock.setStatus(1);
				goodsStock.setCreateBy(UserUtil.getCurUserId());
				goodsStock.setCreateTime(new Date());
				goodsStock.setUpdateBy(UserUtil.getCurUserId());
				goodsStock.setUpdateTime(new Date());

				goodsStockDao.insert(goodsStock);

				this.createStockLog( goodsStock, orderGoods, order, type);

			} else {
				GoodsStock goodsStock = goodsStocks.get(0);
				goodsStock.setShopId(UserUtil.getCurShopId());
				goodsStock.setGoodsId(goods.getId());
				if (type.indexOf("in") != -1) {
					goodsStock.setInQuantity(goodsStock.getInQuantity() + orderGoods.getNum());
					goodsStock.setCurrentStock(orderGoods.getNum() + goodsStock.getCurrentStock());
				} else {
					if (goodsStock.getCurrentStock() < orderGoods.getNum()) {
						result.put("msg", "该商品库存不足，无法出库！");
						return result;
					} else {
						goodsStock.setOutQuentity(goodsStock.getOutQuentity() + orderGoods.getNum());
						goodsStock.setCurrentStock(goodsStock.getCurrentStock() - orderGoods.getNum());
					}
				}
				goodsStock.setUpdateBy(UserUtil.getCurUserId());
				goodsStock.setUpdateTime(new Date());

				goodsStockDao.update(goodsStock);

				this.createStockLog( goodsStock, orderGoods, order, type);

			}
		}
		if (type.equals("in")) order.setStatus(2);
		else if (type.equals("back_in")) order.setStatus(15);
		else if (type.equals("out")) order.setStatus(5);
		else order.setStatus(12);
		order.setInTime(new Date());
		order.setOutTime(new Date());
		order.setUpdateTime(new Date());
		order.setUpdateBy(UserUtil.getCurUserId());
		orderDao.update(order);
		return result;
	}

	@Override
	public JSONObject delete(String orderId, String type) {
		JSONObject result = new JSONObject();
		Order order = new Order(orderId);
		order = orderDao.get(order);
		if (order == null) {
			result.put("msg", "该采购订单不存在");
			return result;
		}
		if (type.equals("in")) order.setStatus(3);
		else if (type.equals("back_in")) order.setStatus(16);
		else if (type.equals("out")) order.setStatus(6);
		else order.setStatus(13);
		order.setUpdateTime(new Date());
		order.setUpdateBy(UserUtil.getCurUserId());
		orderDao.update(order);
		return result;
	}

	private void createStockLog(GoodsStock goodsStock,OrderGoods orderGoods,Order order,String type){

		GoodsStockLog stockLog = new GoodsStockLog();

		if (type.indexOf("in") != -1) {
			stockLog.setPreviousStock(goodsStock.getCurrentStock()-orderGoods.getNum());

		} else {
			stockLog.setPreviousStock(goodsStock.getCurrentStock()+orderGoods.getNum());

		}
		stockLog.setShopId(goodsStock.getShopId());
		stockLog.setGoodsId(goodsStock.getGoodsId());
		stockLog.setQuantity(orderGoods.getNum());
		stockLog.setCurrentStock(goodsStock.getCurrentStock());
		stockLog.setType(type);
		stockLog.setNumber(order.getNumber());
		stockLog.setCreateBy(UserUtil.getCurUserId());
		stockLog.setCreateTime(new Date());
		stockLog.setUpdateBy(UserUtil.getCurUserId());
		stockLog.setUpdateTime(new Date());

		goodsStockLogDao.insert(stockLog);
	}

}