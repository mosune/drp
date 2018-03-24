package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.OperationCode;
import com.drp.data.dao.OperationCodeDao;
import com.drp.service.OperationCodeService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("operationCodeService")
public class OperationCodeServiceImpl implements OperationCodeService {

	@Autowired
	private OperationCodeDao operationCodeDao;

	@Override
	public Object save(OperationCode operationCode) {
		return this.operationCodeDao.insert(operationCode);
	}

	@Override
	public int delete(OperationCode operationCode) {
		return this.operationCodeDao.delete(operationCode);
	}

	@Override
	public int update(OperationCode operationCode) {
		return this.operationCodeDao.update(operationCode);
	}

	@Override
	public OperationCode get(OperationCode operationCode) {
		return this.operationCodeDao.get(operationCode);
	}

}