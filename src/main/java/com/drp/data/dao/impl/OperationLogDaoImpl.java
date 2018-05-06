package com.drp.data.dao.impl;

import com.drp.data.entity.dto.OperationLogDto;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.drp.data.entity.OperationLog;
import com.drp.data.dao.OperationLogDao;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("operationLogDao")
public class OperationLogDaoImpl extends BaseDaoImpl<OperationLog> implements OperationLogDao {

    @Override
    public Page<OperationLogDto> find(PageParam pageParam) {
        Page<OperationLogDto> page = new Page();
        page.setRows(getSqlSession().<OperationLogDto>selectList(getSqlName("selectPage"), pageParam.getMap(), new RowBounds(pageParam.getOffset(), pageParam.getPageSize())));
        Integer count = getSqlSession().selectOne(getSqlName("getCount"), pageParam.getMap());
        page.setTotal(count == null ? 0 : count);
        return page;
    }
}