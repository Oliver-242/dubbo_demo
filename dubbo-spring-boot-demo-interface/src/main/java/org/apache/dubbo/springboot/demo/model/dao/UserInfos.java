package org.apache.dubbo.springboot.demo.model.dao;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.dubbo.springboot.demo.model.TPRegister;

/**
 * @author caijizhou
 * @date 2023/09/05 11:30
 */
@Data
public class UserInfos {
    private long userId;

    private String userName;

    private String phoneNumber;

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

    private String identification;

    public static UserInfos.Builder builder() {
        return new UserInfos.Builder();
    }

    public static UserInfos.Converter converter() {
        return new UserInfos.Converter();
    }

    @Accessors(chain = true)
    public static class Builder {
        @Setter
        private TPRegister tpRegister;

        public UserInfos build() {
            if(this.tpRegister == null) {
                return null;
            }
            UserInfos userInfos = new UserInfos();
            userInfos.setUserName(this.tpRegister.getUserName());
            userInfos.setPhoneNumber(this.tpRegister.getPhoneNumber());
            userInfos.setPassword(this.tpRegister.getPassword());
            userInfos.setNickName(this.tpRegister.getNickName());
            userInfos.setIdentification("user");
            return userInfos;
        }
    }

    @Accessors(chain = true)
    public static class Converter {
        @Setter
        private UserInfos userInfos;

        public TPRegister toTPRegister() {
            if(userInfos == null) {
                return null;
            }
            TPRegister tpRegister = new TPRegister();
            tpRegister.setUserName(this.userInfos.getUserName());
            tpRegister.setPhoneNumber(this.userInfos.getPhoneNumber());
            tpRegister.setUserName(this.userInfos.getPassword());
            tpRegister.setUserName(this.userInfos.getNickName());
            return tpRegister;
        }
    }
}
