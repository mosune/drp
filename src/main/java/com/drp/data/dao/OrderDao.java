package com.drp.data.dao;

import com.drp.data.entity.Order;
import com.drp.data.entity.dto.OrderGoodsDto;
import com.drp.util.Page;
import com.drp.util.PageParam;

import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
public interface OrderDao extends BaseDao<Order> {

    /**
     * 获取列表
     * @param pageParam
     * @return
     */
    Page<Order> find(PageParam pageParam);

    /**
     * 通过订单id获取货物列表
     * @param id
     * @return
     */
    List<OrderGoodsDto> getGoods(String id);
}