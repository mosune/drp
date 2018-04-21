package com.drp.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.drp.data.entity.OrderGoods;
import com.drp.data.dao.OrderGoodsDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<OrderGoods> findOrderGoods(String orderId) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("orderId",orderId);
        List<OrderGoods> orderGoodsList = getSqlSession().selectList(getSqlName("selectBy"), map);
        return orderGoodsList;
    }
}