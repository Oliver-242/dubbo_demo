package org.apache.dubbo.springboot.demo;

public class TReturn {
    // 0 正常；1 用户不存在；2 余额不足
    public int status;

    public long data;

     public String returnString;

    public TReturn() {
        status = 0;
        data = -1;
        returnString = null;
    }
}
