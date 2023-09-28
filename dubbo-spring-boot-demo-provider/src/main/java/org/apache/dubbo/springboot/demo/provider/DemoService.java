package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author oliver
 * @date 2023/08/11 11:00
 */
public interface DemoService {
    TReturn repay(TParam tParam) throws Exception;

    TReturn deposit(TParam tParam) throws Exception;

    TReturn withdraw(TParam tParam) throws Exception;

    TReturn transfer(TParam tParam) throws Exception;

    CompletableFuture<TReturn> inquireAsync(TParam tParam) throws Exception;

    TReturn inquire(TParam tParam) throws Exception;

    Boolean verify(List<String> cardId, long userId, String methodName);
}
