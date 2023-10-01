package org.apache.dubbo.springboot.demo.mapper;

import org.apache.dubbo.springboot.demo.model.entity.DepositCards;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author oliver
 * @date 2023/08/17 15:00
 */
@Mapper
@Repository
public interface DepositCardsDao {
    @Select("select * from depositcards order by userid")
    List<DepositCards> queryAllCardInfo();

    @Select(("select cardid from depositcards where userid=#{userId} and status=#{status}"))
    List<String> queryAllValidCardIdByUserId(@Param("userId") long userId, @Param("status") String status);

    @Select("select money from depositcards where cardid=#{cardId}")
    Long selectMoneyByCardId(@Param("cardId") String cardId);

    @Update("update depositcards set money=money+#{money} where cardid=#{cardId} and status='正常'")
    int depositMoneyByCardId(@Param("cardId") String cardId, @Param("money") long money);

    @Update("update depositcards set money=money-#{money} where cardid=#{cardId} and money>=#{money} and status='正常'")
    int withdrawMoneyByCardId(@Param("cardId") String cardId, @Param("money") long money);

    @Delete("delete from depositcards where cardid=#{cardId}")
    int deleteCardByCardId(@Param("cardId") String cardId);

    @Delete("delete from depositcards where userid=#{userId}")
    int deleteCardByUserId(@Param("userId") long userId);
}
