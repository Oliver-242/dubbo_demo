package org.apache.dubbo.springboot.demo;

public interface DemoService {
    String deposit(String cardId, long money) throws Exception;

    String withdraw(String cardId, long money) throws Exception;

    String transfer(String sender, String receiver, long money) throws Exception;

    String inquire(String cardId) throws Exception;
}
