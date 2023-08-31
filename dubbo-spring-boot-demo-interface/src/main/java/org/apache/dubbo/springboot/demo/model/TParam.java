package org.apache.dubbo.springboot.demo.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author oliver
 * @date 2023/08/17 10:30
 */
@Data
public class TParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 7833252070488339211L;

    private String firstAccount;

    private String secondAccount;

    private long money;

    public TParam(String firstAccount, String secondAccount, long money) {
        this.firstAccount = firstAccount;
        this.secondAccount = secondAccount;
        this.money = money;
    }

    public TParam(String firstAccount) {
        this.firstAccount = firstAccount;
        this.secondAccount = null;
        this.money = -1;
    }

    public TParam(String firstAccount, long money) {
        this.firstAccount = firstAccount;
        this.secondAccount = null;
        this.money = money;
    }
}
