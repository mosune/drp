package com.drp.data.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * FileName: Role
 *
 * @author gcg
 * @create 2018/01/03 10:06
 * Description: 职位
 * History:
 **/
@Data
@Builder
public class Role implements Serializable {

    // id
    private int id;

    // 职位code
    private String code;

    // 职位名称
    private String name;

}
