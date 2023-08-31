package org.apache.dubbo.springboot.demo.mapper;

import org.apache.dubbo.springboot.demo.model.TransactionRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author caijizhou
 * @date 2023/08/30 18:20
 */
@Mapper
@Repository
public interface TransactionRecordsDao {
    List<TransactionRecords> queryAllRecords();

    List<TransactionRecords> queryByUserId(long userId);

    int saveRecord(@Param("record") TransactionRecords transactionRecords);
}
