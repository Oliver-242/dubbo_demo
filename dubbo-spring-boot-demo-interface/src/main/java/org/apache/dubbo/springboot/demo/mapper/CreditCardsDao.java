package org.apache.dubbo.springboot.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CreditCardsDao {
    @Select(("select cardid from creditcards where userid=#{userId}"))
    List<String> queryAllCardIdByUserId(@Param("userId") long userId);
}
