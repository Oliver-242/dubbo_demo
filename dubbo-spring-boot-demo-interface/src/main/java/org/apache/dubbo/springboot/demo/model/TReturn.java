package org.apache.dubbo.springboot.demo.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author oliver
 * @date 2023/08/17 10:30
 */
@Data
public class TReturn<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -810721767445082952L;
    // 0 正常；1 用户不存在；2 余额不足
    public int status;

    public T data;

     public String returnString;

    public TReturn() {
        status = 0;
        returnString = null;
    }
}
