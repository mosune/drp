package com.drp.data.entity.dto;

import com.drp.data.entity.GoodsStock;
import lombok.Data;

/**
 * 商品库存dto
 *
 * @author gcg
 * @create 2018-05-06 15:13
 **/
@Data
public class GoodsStockDto extends GoodsStock {

    // 商品id
    private Integer goodsId;

    // 商品名称
    private String goodsName;

    // 品类名称
    private String cateName;

}
