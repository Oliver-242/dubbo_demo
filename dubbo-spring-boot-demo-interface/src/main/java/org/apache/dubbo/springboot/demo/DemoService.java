package org.apache.dubbo.springboot.demo;

public interface DemoService {
    TReturn deposit(TParam tParam) throws Exception;

    TReturn withdraw(TParam tParam) throws Exception;

    TReturn transfer(TParam tParam) throws Exception;

    TReturn inquire(TParam tParam) throws Exception;
}
