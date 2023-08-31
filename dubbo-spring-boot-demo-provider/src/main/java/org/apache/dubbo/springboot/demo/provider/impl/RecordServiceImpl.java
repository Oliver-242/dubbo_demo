package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.mapper.TransactionRecordsDao;
import org.apache.dubbo.springboot.demo.model.TransactionRecords;
import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;
import org.apache.dubbo.springboot.demo.provider.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /**
     * 用户每进行一条操作，就在系统中留下一条记录
     * @param saveRecordDto 整合多方信息组成流水单号的要素
     */
    @Override
    public void saveRecord(SaveRecordDto saveRecordDto) {
        TransactionRecords transactionRecords = new TransactionRecords();
        String id = generateId();

        transactionRecords.setId(id);
        transactionRecords.setUserId(saveRecordDto.getUserId());
        transactionRecords.setTypeId(saveRecordDto.getTypeId());
        transactionRecords.setStatus(saveRecordDto.getTReturn().getStatus());
        transactionRecords.setFirstCard(saveRecordDto.getTParam().getFirstAccount());
        transactionRecords.setSecondCard(saveRecordDto.getTParam().getSecondAccount());
        transactionRecords.setMoney(saveRecordDto.getTReturn().getData());

        transactionRecordsDao.saveRecord(transactionRecords);
    }

    /**
     * 用于生成14位随机流水单号：8位日期 + 6位随机数
     * @return 14位流水单号
     */
    private String generateId(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = localDate.format(formatter);

        int randomNumber = ThreadLocalRandom.current().nextInt(1000000);
        String formatNumber = String.format("%06d", randomNumber);

        return currentDate + formatNumber;
    }
}
