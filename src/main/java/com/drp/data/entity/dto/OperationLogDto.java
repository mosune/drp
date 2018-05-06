package com.drp.data.entity.dto;

import com.drp.data.entity.OperationLog;
import lombok.Data;

/**
 * log的dto
 * @author gcg
 * @create 2018-05-06 16:28
 **/
@Data
public class OperationLogDto extends OperationLog {

    // 用户名
    private String userName;

}
