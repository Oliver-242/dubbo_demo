package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.mapper.TransactionRecordsDao;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.apache.dubbo.springboot.demo.model.TransactionRecords;
import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;
import org.apache.dubbo.springboot.demo.provider.RecordService;
import org.apache.dubbo.springboot.demo.provider.SnowService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author caijizhou
 * @date 2023/08/31 9:50
 */
@DubboService(group = "group1", version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Resource
    private TransactionRecordsDao transactionRecordsDao;

    @DubboReference(group = "group1", version = "1.0.0")
    private SnowService snowService;

    /**
     * 用户每进行一条操作，就在系统中留下一条记录
     * @param saveRecordDto 整合多方信息组成流水单号的要素
     */
    @Override
    @Async
    public void saveRecord(SaveRecordDto<TReturn> saveRecordDto) {
        TransactionRecords transactionRecords = new TransactionRecords();

        String id = snowService.getGeneratedId();

        transactionRecords.setId(id);
        transactionRecords.setUserId(saveRecordDto.getUserId());
        transactionRecords.setTypeId(saveRecordDto.getTypeId());
        transactionRecords.setStatus(saveRecordDto.getTReturn().getStatus());
        transactionRecords.setFirstCard(saveRecordDto.getTParam().getFirstAccount());
        transactionRecords.setSecondCard(saveRecordDto.getTParam().getSecondAccount());
        transactionRecords.setMoney(saveRecordDto.getTReturn().getData());

        transactionRecordsDao.saveRecord(transactionRecords);
    }

    @Override
    @Async
    public void saveRecordAsync(SaveRecordDto<CompletableFuture<TReturn>> saveRecordDto) throws Exception {
        TransactionRecords transactionRecords = new TransactionRecords();

        String id = snowService.getGeneratedId();

        transactionRecords.setId(id);
        transactionRecords.setUserId(saveRecordDto.getUserId());
        transactionRecords.setTypeId(saveRecordDto.getTypeId());
        transactionRecords.setStatus(saveRecordDto.getTReturn().get().getStatus());
        transactionRecords.setFirstCard(saveRecordDto.getTParam().getFirstAccount());
        transactionRecords.setSecondCard(saveRecordDto.getTParam().getSecondAccount());
        transactionRecords.setMoney(saveRecordDto.getTReturn().get().getData());

        transactionRecordsDao.saveRecord(transactionRecords);
    }

    /**
     * 用于生成16位随机流水单号：2位操作类型 + 8位日期 + 6位随机数
     * @return 16位流水单号
     * @deprecated 采用snowflake算法作为64位流水单号生成
     */
    @Deprecated
    private String generateId(String typeId){
        String prefixId = switch (typeId) {
            case "transfer" -> "ZZ";
            case "deposit" -> "CK";
            case "withdraw" -> "TK";
            case "query" -> "CX";
            default -> throw new UnsupportedOperationException("无匹配操作！");
        };

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = localDate.format(formatter);

        int randomNumber = ThreadLocalRandom.current().nextInt(1000000);
        String formatNumber = String.format("%06d", randomNumber);

        return prefixId + currentDate + formatNumber;
    }
}
