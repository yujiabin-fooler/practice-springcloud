package org.jiabin.seata.tcc.account.practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_account")
public class Account {
    private Integer id;

    private Integer userId;
    
    private Integer amount;
}
