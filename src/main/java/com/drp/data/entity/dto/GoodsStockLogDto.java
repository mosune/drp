package com.drp.data.entity.dto;

import com.drp.data.entity.GoodsStockLog;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 货物进出库记录dto
 *
 * @author gcg
 * @create 2018-05-19 17:46
 **/
@Data
public class GoodsStockLogDto extends GoodsStockLog {

    private BigDecimal originalPrice;

    private BigDecimal salePrice;

}
