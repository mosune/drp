package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import com.drp.data.entity.dto.OperationLogDto;
import com.drp.util.Page;
import com.drp.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.OperationLog;
import com.drp.data.dao.OperationLogDao;
import com.drp.service.OperationLogService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private OperationLogDao operationLogDao;

	@Override
	public Object save(OperationLog operationLog) {
		return this.operationLogDao.insert(operationLog);
	}

	@Override
	public int delete(OperationLog operationLog) {
		return this.operationLogDao.delete(operationLog);
	}

	@Override
	public int update(OperationLog operationLog) {
		return this.operationLogDao.update(operationLog);
	}

	@Override
	public OperationLog get(OperationLog operationLog) {
		return this.operationLogDao.get(operationLog);
	}

	@Override
	public Page<OperationLogDto> find(PageParam pageParam) {
		return this.operationLogDao.find(pageParam);
	}

}