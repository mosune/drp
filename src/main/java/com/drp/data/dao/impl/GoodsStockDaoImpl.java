package com.drp.data.dao.impl;

import com.drp.data.entity.dto.GoodsStockDto;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.GoodsStock;
import com.drp.data.dao.GoodsStockDao;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("goodsStockDao")
public class GoodsStockDaoImpl extends BaseDaoImpl<GoodsStock> implements GoodsStockDao {

    @Override
    public Page<GoodsStockDto> find(PageParam pageParam) {
        Page<GoodsStockDto> page = new Page();
        page.setRows(getSqlSession().<GoodsStockDto>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }
}