package com.drp.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.drp.data.entity.Order;
import com.drp.data.dao.OrderDao;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {
	
}