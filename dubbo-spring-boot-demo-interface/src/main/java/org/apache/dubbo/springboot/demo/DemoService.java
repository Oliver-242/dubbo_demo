package org.apache.dubbo.springboot.demo;

public interface DemoService {
    void sayHello();
    Boolean deposit(String cardId, int money) throws Exception;

    Boolean withdraw(String cardId, int money);

    Boolean transfer(String sender, String receiver, int money);

    Boolean inquire(String cardId);
}
