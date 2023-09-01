package org.apache.dubbo.springboot.demo.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author oliver
 * @date 2023/08/17 10:30
 */
@Data
public class TReturn implements Serializable {
    @Serial
    private static final long serialVersionUID = -810721767445082952L;

    // 0 正常；1 用户不存在；2 余额不足
    private int status;

    private long data;

    private String returnString;

//    public String getReturnString() {
//        return returnString;
//    }

    public TReturn() {
        status = 0;
        data = -1L;
        returnString = null;
    }
}
