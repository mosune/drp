package com.drp.data.dao;

import com.drp.data.entity.GoodsStock;
import com.drp.data.entity.dto.GoodsStockDto;
import com.drp.util.Page;
import com.drp.util.PageParam;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface GoodsStockDao extends BaseDao<GoodsStock> {
    /**
     * 获取分页数据
     * @param pageParam
     * @return
     */
    Page<GoodsStockDto> find(PageParam pageParam);
}