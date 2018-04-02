package com.drp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.OrderGoods;
import com.drp.data.dao.OrderGoodsDao;
import com.drp.service.OrderGoodsService;


/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements OrderGoodsService {

	@Autowired
	private OrderGoodsDao orderGoodsDao;

	@Override
	public Object save(OrderGoods orderGoods) {
		return this.orderGoodsDao.insert(orderGoods);
	}

	@Override
	public int delete(OrderGoods orderGoods) {
		return this.orderGoodsDao.delete(orderGoods);
	}

	@Override
	public int update(OrderGoods orderGoods) {
		return this.orderGoodsDao.update(orderGoods);
	}

	@Override
	public OrderGoods get(OrderGoods orderGoods) {
		return this.orderGoodsDao.get(orderGoods);
	}

}