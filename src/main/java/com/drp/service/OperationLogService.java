package com.drp.service;

import com.drp.data.entity.OperationLog;
import com.drp.data.entity.dto.OperationLogDto;
import com.drp.util.Page;
import com.drp.util.PageParam;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface OperationLogService {

	/**
	 * 保存
	 * @param operationLog
	 * @return
	 */
	Object save(OperationLog operationLog);
	
	/**
	 * 删除（按主键）
	 * @param operationLog
	 * @return
	 */
	int delete(OperationLog operationLog);

	/**
	 * 修改（按主键）
	 * @param operationLog
	 * @return
	 */
	int update(OperationLog operationLog);

	/**
	 * 获取（按主键）
	 * @param operationLog
	 * @return
	 */
	OperationLog get(OperationLog operationLog);

    Page<OperationLogDto> find(PageParam pageParam);
}