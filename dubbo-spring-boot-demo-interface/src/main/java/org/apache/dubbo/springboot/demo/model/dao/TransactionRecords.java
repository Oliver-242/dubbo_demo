package org.apache.dubbo.springboot.demo.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author caijizhou
 * @date 2023/08/30 18:00
 */
@Data
@NoArgsConstructor
public class TransactionRecords {
    private String id;

    private long userId;

    /**
     * 交易类型：transfer, deposit, etc.
     */
    private String typeId;

    private int status;

    private String firstCard;

    /**
     * 当交易只涉及一张卡时, 此字段设置为"0000000000000"
     */
    private String secondCard;

    private long money;

    private String createTime;

}
