package org.apache.dubbo.springboot.demo.model.dao;

import lombok.Data;

/**
 * @author caijizhou
 * @date 2023/09/05 11:30
 */
@Data
public class UserInfos {
    private long userId;

    private String userName;

    /**
     * 不超过16位
     */
    private String password;

    /**
     * 可为null
     */
    private String nickName;

    /**
     * 上限两张
     */
    private int creditCardNum;

    /**
     * 上限三张
     */
    private int depositCardNum;
}
