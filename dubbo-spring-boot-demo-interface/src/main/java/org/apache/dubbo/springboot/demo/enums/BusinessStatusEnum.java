package org.apache.dubbo.springboot.demo.enums;

import lombok.Getter;

/**
 * @author caijizhou
 * @date 2023/09/07 15:30
 */
@Getter
public enum BusinessStatusEnum {
    ACTIVE("正常"),
    FROZEN("冻结"),
    SUCCESS("成功"),
    FAILED("失败");

    private final String status;

    BusinessStatusEnum(String status) {
        this.status = status;
    }
}
