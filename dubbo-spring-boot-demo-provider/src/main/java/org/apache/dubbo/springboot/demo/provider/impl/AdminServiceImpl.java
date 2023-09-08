package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.mapper.DepositCardsDao;
import org.apache.dubbo.springboot.demo.mapper.TransactionRecordsDao;
import org.apache.dubbo.springboot.demo.mapper.UserInfosDao;
import org.apache.dubbo.springboot.demo.model.dao.DepositCards;
import org.apache.dubbo.springboot.demo.model.dao.TransactionRecords;
import org.apache.dubbo.springboot.demo.model.dao.UserInfos;
import org.apache.dubbo.springboot.demo.provider.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author caijizhou
 * @date 2023/09/08 10:20
 */
@DubboService(group = "group1", version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserInfosDao userInfosDao;

    @Autowired
    private DepositCardsDao depositCardsDao;

    @Autowired
    private TransactionRecordsDao transactionRecordsDao;

    @Override
    public List<UserInfos> queryAllUserInfo() {
        return this.userInfosDao.queryAllUserInfo();
    }

    @Override
    public List<DepositCards> queryAllDeCardsInfo() {
        return this.depositCardsDao.queryAllCardInfo();
    }

    @Override
    public List<TransactionRecords> queryAllRecordInfo() {
        return this.transactionRecordsDao.queryAllRecords();
    }
}
