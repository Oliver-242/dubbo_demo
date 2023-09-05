package org.apache.dubbo.springboot.demo.enums;

import lombok.Getter;


/**
 * @author caijizhou
 * @date 2023/09/04 11:25
 */
@Getter
public enum RecordTypeEnum {
    TRANSFER("transfer"),
    QUERY("query"),
    WITHDRAW("withdraw"),
    DEPOSIT("deposit"),
    LOGIN("login"),
    REGISTER("register");

    private final String desc;

    RecordTypeEnum(String desc) {
        this.desc = desc;
    }
}
