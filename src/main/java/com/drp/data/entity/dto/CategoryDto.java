package com.drp.data.entity.dto;

import com.drp.data.entity.Category;
import lombok.Data;

/**
 * Category Dto
 *
 * @author gcg
 * @create 2018-04-02 10:38
 **/
@Data
public class CategoryDto extends Category {

    private String levelName; // 上级名称

}
