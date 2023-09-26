package org.apache.dubbo.springboot.demo.enums;

import lombok.Getter;

/**
 * @author caijizhou
 * @date 2023/09/25 18:00
 */
@Getter
public enum UserTypeEnum {
    USER("user"),
    ADMIN("admin");

    private final String userType;

    UserTypeEnum(String userType) {
        this.userType = userType;
    }
}
