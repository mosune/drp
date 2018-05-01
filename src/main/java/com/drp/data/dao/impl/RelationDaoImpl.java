package com.drp.data.dao.impl;

import org.springframework.stereotype.Repository;

import com.drp.data.entity.Relation;
import com.drp.data.dao.RelationDao;

import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
@Repository("relationDao")
public class RelationDaoImpl extends BaseDaoImpl<Relation> implements RelationDao {

    @Override
    public List<Integer> getRelation(Integer id) {
        return getSqlSession().selectList(getSqlName("getRelation"), id);
    }

    @Override
    public int deleteByRoleId(int id) {
        return getSqlSession().delete(getSqlName("deleteByRoleId"), id);
    }
}