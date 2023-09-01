package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * @author oliver
 * @date 2023/08/11 11:00
 */
public interface DemoService {
    TReturn deposit(TParam tParam) throws Exception;

    TReturn withdraw(TParam tParam) throws Exception;

    TReturn transfer(TParam tParam) throws Exception;
    @Async
    CompletableFuture<TReturn> inquire(TParam tParam) throws Exception;
}
