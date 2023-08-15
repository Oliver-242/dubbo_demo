package com.bamboo.mapper;

public interface DepositCardsMapper {
    Long selectMoneyByCardId(String cardId);

    void depositMoneyByCardId(String cardId, long money);

    void withdrawMoneyByCardId(String cardId, long money);

    void transferMoney(String sender, String receiver, long money);
}
