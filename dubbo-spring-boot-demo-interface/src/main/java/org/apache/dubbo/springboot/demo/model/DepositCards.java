package org.apache.dubbo.springboot.demo.model;

import lombok.Data;

/**
 * @author caijizhou
 * @date 2023/08/17 10:00
 */
@Data
public class DepositCards {
    private String userName;

    private long userId;

    private String cardId;

    private long money;

    private String createTime;

    private String updateTime;

}
