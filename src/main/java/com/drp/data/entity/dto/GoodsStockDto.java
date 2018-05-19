package com.drp.data.entity.dto;

import com.drp.data.entity.GoodsStock;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品库存dto
 *
 * @author gcg
 * @create 2018-05-06 15:13
 **/
@Data
public class GoodsStockDto extends GoodsStock {

    // 商品id
    private String goodsId;

    // 商品名称
    private String goodsName;

    // 品类名称
    private String cateName;

    // 利润
    private BigDecimal price;

    // 成本价
    private BigDecimal originalPrice;

    // 售卖价
    private BigDecimal salePrice;

}
