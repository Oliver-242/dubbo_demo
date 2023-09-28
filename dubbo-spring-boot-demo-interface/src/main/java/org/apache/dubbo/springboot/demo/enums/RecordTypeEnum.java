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
    REPAY("repay"),
    LOGIN("login"),
    REGISTER("register"),
    FREEZEUSER("freeze_user"),
    RESTOREUSER("restore_user"),
    DELETEUSER("delete_user");

    private final String desc;

    RecordTypeEnum(String desc) {
        this.desc = desc;
    }
}
