package org.apache.dubbo.springboot.demo.enums;

import lombok.Getter;


/**
 * @author caijizhou
 * @date 2023/09/04 11:25
 */
@Getter
public enum ServiceTypeEnum {
    TRANSFER("transfer"),
    QUERY("query"),
    WITHDRAW("withdraw"),
    DEPOSIT("deposit");

    private final String desc;

    ServiceTypeEnum(String desc) {
        this.desc = desc;
    }
}
