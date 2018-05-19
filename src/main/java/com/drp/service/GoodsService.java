package com.drp.service;

import com.alibaba.fastjson.JSONObject;
import com.drp.data.entity.Goods;
import com.drp.util.Page;
import com.drp.util.PageParam;

import java.util.List;

/**
 * 
 * @author gcg
 * @date 2018-03-24 24:53:08
 */
public interface GoodsService {

	/**
	 * 保存
	 * @param goods
	 * @return
	 */
	Object save(Goods goods);
	
	/**
	 * 删除（按主键）
	 * @param goods
	 * @return
	 */
	int delete(Goods goods);

	/**
	 * 修改（按主键）
	 * @param goods
	 * @return
	 */
	int update(Goods goods);

	/**
	 * 获取（按主键）
	 * @param goods
	 * @return
	 */
	Goods get(Goods goods);

	/**
	 * 获取首页列表
	 * @param pageParam
	 * @return
	 */
	Page<Goods> find(PageParam pageParam);

	/**
	 * 添加或者修改货物
	 * @param goods
	 * @param originalAmount
     * @return
	 */
    JSONObject addOrUpdate(Goods goods, String originalAmount);

	/**
	 * 软删除
	 * @param goods
	 */
	JSONObject deleteLoft(Goods goods);

	/**
	 * 修改状态
	 * @param goods
	 * @return
	 */
	JSONObject updateStatus(Goods goods);

	/**
	 * 获取货物列表
	 * @return
	 */
    List<Goods> getGoodsList();

    JSONObject getdata(Goods goods);
}