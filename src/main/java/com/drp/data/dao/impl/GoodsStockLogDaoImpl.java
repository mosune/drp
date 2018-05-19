package com.drp.data.dao.impl;

import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.GoodsStockLog;
import com.drp.data.dao.GoodsStockLogDao;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("goodsStockLogDao")
public class GoodsStockLogDaoImpl extends BaseDaoImpl<GoodsStockLog> implements GoodsStockLogDao {

    @Override
    public Page<GoodsStockLog> find(PageParam pageParam) {
        Page<GoodsStockLog> page = new Page();
        page.setRows(getSqlSession().<GoodsStockLog>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }

    @Override
    public List<GoodsStockLog> getListAndPrice(HashMap<String, Object> map) {
        return getSqlSession().selectList(getSqlName("getListAndPrice"), map);
    }
}