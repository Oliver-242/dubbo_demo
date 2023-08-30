package com.bamboo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author oliver
 * @date 2023/08/17 15:00
 */
@Mapper
@Repository
public interface DepositCardsDao {
    @Select("select money from depositcards where cardid=#{cardId}")
    Long selectMoneyByCardId(@Param("cardId") String cardId);

    @Update("update depositcards set money=money+#{money} where cardid=#{cardId}")
    int depositMoneyByCardId(@Param("cardId") String cardId, @Param("money") long money);

    @Update("update depositcards set money=money-#{money} where cardid=#{cardId} and money>=#{money}")
    int withdrawMoneyByCardId(@Param("cardId") String cardId, @Param("money") long money);
}
