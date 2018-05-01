package com.drp.data.dao;

import com.drp.data.entity.Relation;

import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface RelationDao extends BaseDao<Relation> {

    /**
     *
     * @param id
     * @return
     */
    List<Integer> getRelation(Integer id);

    int deleteByRoleId(int id);
}