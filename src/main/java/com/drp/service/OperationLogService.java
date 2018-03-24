package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.OperationLog;

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

}