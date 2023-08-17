package com.bamboo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DepositCardsDao {
    @Select("select money from depositcards where cardid=#{cardId}")
    Long selectMoneyByCardId(@Param("cardId") String cardId);

    @Update("update depositcards set money=money+#{money} where cardid=#{cardId}")
    int depositMoneyByCardId(@Param("cardId") String cardId, @Param("money") long money);

    @Update("update depositcards set money=money-#{money} where cardid=#{cardId} and money>=#{money}")
    int withdrawMoneyByCardId(@Param("cardId") String cardId, @Param("money") long money);
}
