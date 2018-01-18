package com.drp.data.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * FileName: User
 *
 * @author gcg
 * @create 2018/01/17 10:08
 * Description: 用户对象
 * History:
 **/
@Data
@Builder
public class User implements Serializable {

    // 用户id
    private int id;

    // 用户名
    private String userName;

    // 用户密码
    private String password;

}
