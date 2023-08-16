package org.apache.dubbo.springboot.demo;

public class TParam {
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
