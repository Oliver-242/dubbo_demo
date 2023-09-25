package org.apache.dubbo.springboot.demo.enums;

/**
 * @author caijizhou
 * @date 2023/09/25 18:00
 */

public enum UserTypeEnum {
    USER("user"),
    ADMIN("admin");

    private final String userType;

    UserTypeEnum(String userType) {
        this.userType = userType;
    }
}
