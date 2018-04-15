package com.drp.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.drp.data.entity.OrderGoods;
import com.drp.data.dao.OrderGoodsDao;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
@Repository("orderGoodsDao")
public class OrderGoodsDaoImpl extends BaseDaoImpl<OrderGoods> implements OrderGoodsDao {

    @Override
    public void deleteByOrderId(String orderId) {
        getSqlSession().delete(getSqlName("deleteByOrderId"), orderId);
    }
}