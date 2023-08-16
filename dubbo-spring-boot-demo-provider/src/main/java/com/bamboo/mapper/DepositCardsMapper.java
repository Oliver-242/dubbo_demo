package com.bamboo.mapper;

public interface DepositCardsMapper {
    Long selectMoneyByCardId(String cardId);

    int depositMoneyByCardId(String cardId, long money);

    int withdrawMoneyByCardId(String cardId, long money);
}
