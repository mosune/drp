package com.drp.data.dao;

import com.drp.data.entity.OperationLog;
import com.drp.data.entity.dto.OperationLogDto;
import com.drp.util.Page;
import com.drp.util.PageParam;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface OperationLogDao extends BaseDao<OperationLog> {

    Page<OperationLogDto> find(PageParam pageParam);
}