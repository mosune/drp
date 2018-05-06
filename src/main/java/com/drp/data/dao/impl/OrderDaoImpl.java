package com.drp.data.dao.impl;

import com.drp.data.entity.dto.OrderGoodsDto;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.Order;
import com.drp.data.dao.OrderDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gcg
 * @date 2018-04-02 02:28:22
 */
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    @Override
    public Page<Order> find(PageParam pageParam) {
        Page<Order> page = new Page();
        page.setRows(getSqlSession().<Order>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }

    @Override
    public List<OrderGoodsDto> getGoods(String id) {
        return getSqlSession().selectList(getSqlName("getGoodsList"), id);
    }
}