package org.apache.dubbo.springboot.demo;

/**
 * @author oliver
 * @date 2023/08/11 11:00
 */
public interface DemoService {
    TReturn deposit(TParam tParam) throws Exception;

    TReturn withdraw(TParam tParam) throws Exception;

    TReturn transfer(TParam tParam) throws Exception;

    TReturn inquire(TParam tParam) throws Exception;
}
