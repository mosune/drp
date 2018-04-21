package com.drp.data.dao.impl;

import com.drp.data.entity.GoodsStockLog;
import com.drp.data.entity.OrderGoods;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.GoodsStock;
import com.drp.data.dao.GoodsStockDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("goodsStockDao")
public class GoodsStockDaoImpl extends BaseDaoImpl<GoodsStock> implements GoodsStockDao {

    @Override
    public Page<GoodsStock> find(PageParam pageParam) {
        Page<GoodsStock> page = new Page();
        page.setRows(getSqlSession().<GoodsStock>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }

    @Override
    public GoodsStock findGoodsStock(Integer goodsId) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("orderId",goodsId);
        GoodsStock goodsStock = getSqlSession().selectOne(getSqlName("selectBy"), map);
        return goodsStock;
    }
}