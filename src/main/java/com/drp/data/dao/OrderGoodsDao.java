package com.drp.data.dao;

import com.drp.data.entity.OrderGoods;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
public interface OrderGoodsDao extends BaseDao<OrderGoods> {

    /**
     * 通过orderId删除
     * @param orderId
     */
    void deleteByOrderId(String orderId);
}