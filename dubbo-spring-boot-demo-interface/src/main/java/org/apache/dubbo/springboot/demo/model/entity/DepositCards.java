package org.apache.dubbo.springboot.demo.model.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author caijizhou
 * @date 2023/08/17 10:00
 */
@Data
public class DepositCards implements Serializable {
    @Serial
    private static final long serialVersionUID = -7593222070496677886L;

    private long userId;

    private String cardId;

    private long money;

    private String status;

    private String createTime;

    private String updateTime;

}
