package org.apache.dubbo.springboot.demo.model.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CreditCards implements Serializable {
    @Serial
    private static final long serialVersionUID = -1362343886756373721L;

    private String userName;

    private long userId;

    private String cardId;

    private long money;

    private String status;

    private String createTime;

    private String updateTime;
}
