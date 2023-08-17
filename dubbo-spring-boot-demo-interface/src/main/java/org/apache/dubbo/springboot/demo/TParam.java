package org.apache.dubbo.springboot.demo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class TParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 7833252070488339211L;
    public String firstAccount;

    public String secondAccount;

    public long money;

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
