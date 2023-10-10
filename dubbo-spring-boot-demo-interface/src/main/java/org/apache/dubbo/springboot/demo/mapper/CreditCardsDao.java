package org.apache.dubbo.springboot.demo.mapper;

import org.apache.dubbo.springboot.demo.model.entity.CreditCards;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author caijizhou
 * @date 2023/09/30 11:00
 */
@Mapper
@Repository
public interface CreditCardsDao {
    @Select("select * from creditcards")
    List<CreditCards> queryAllCardInfo();

    @Select(("select cardid from creditcards where userid=#{userId} and status=#{status}"))
    List<String> queryAllValidCardIdByUserId(@Param("userId") long userId, @Param("status") String status);
}
