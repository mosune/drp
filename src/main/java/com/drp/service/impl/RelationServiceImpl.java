package com.drp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.data.entity.Relation;
import com.drp.data.dao.RelationDao;
import com.drp.service.RelationService;


/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Service("relationService")
public class RelationServiceImpl implements RelationService {

	@Autowired
	private RelationDao relationDao;

	@Override
	public Object save(Relation relation) {
		return this.relationDao.insert(relation);
	}

	@Override
	public int delete(Relation relation) {
		return this.relationDao.delete(relation);
	}

	@Override
	public int update(Relation relation) {
		return this.relationDao.update(relation);
	}

	@Override
	public Relation get(Relation relation) {
		return this.relationDao.get(relation);
	}

}