package com.drp.data.entity.dto;

import com.drp.data.entity.Goods;
import lombok.Data;

/**
 * goods dto
 *
 * @author gcg
 * @create 2018-04-02 20:41
 **/
@Data
public class GoodsDto extends Goods {

    private String cateName; // 类目名称

}
