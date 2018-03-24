package com.drp.service;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.OperationCode;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface OperationCodeService {

	/**
	 * 保存
	 * @param operationCode
	 * @return
	 */
	Object save(OperationCode operationCode);
	
	/**
	 * 删除（按主键）
	 * @param operationCode
	 * @return
	 */
	int delete(OperationCode operationCode);

	/**
	 * 修改（按主键）
	 * @param operationCode
	 * @return
	 */
	int update(OperationCode operationCode);

	/**
	 * 获取（按主键）
	 * @param operationCode
	 * @return
	 */
	OperationCode get(OperationCode operationCode);

}