package com.drp.data.entity.dto;

import com.drp.data.entity.AdminUser;
import lombok.Data;

/**
 * 用户信息dto
 *
 * @author gcg
 * @create 2018-04-29 16:13
 **/
@Data
public class AdminUserDto extends AdminUser {

    private String shopName;

    private String roleName;

}
