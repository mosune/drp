package com.drp.data.entity.dto;

import com.drp.data.entity.OrderGoods;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单货物信息
 *
 * @author gcg
 * @create 2018-04-26 12:01
 **/
@Data
public class OrderGoodsDto extends OrderGoods {

    // 货物名称
    private String goodsName;

    private BigDecimal originalPrice; //成本价格

}
