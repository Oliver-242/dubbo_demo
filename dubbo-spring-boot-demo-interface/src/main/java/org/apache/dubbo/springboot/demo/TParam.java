package org.apache.dubbo.springboot.demo;

import java.io.Serializable;

public class TParam implements Serializable {
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
