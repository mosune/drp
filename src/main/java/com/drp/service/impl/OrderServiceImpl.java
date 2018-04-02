package com.drp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Order;
import com.drp.data.dao.OrderDao;
import com.drp.service.OrderService;


/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

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

}