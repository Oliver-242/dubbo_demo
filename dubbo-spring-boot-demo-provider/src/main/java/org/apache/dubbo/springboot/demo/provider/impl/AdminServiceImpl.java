package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.enums.BusinessStatusEnum;
import org.apache.dubbo.springboot.demo.mapper.CreditCardsDao;
import org.apache.dubbo.springboot.demo.mapper.DepositCardsDao;
import org.apache.dubbo.springboot.demo.mapper.TransactionRecordsDao;
import org.apache.dubbo.springboot.demo.mapper.UserInfosDao;
import org.apache.dubbo.springboot.demo.model.TPAdminButton;
import org.apache.dubbo.springboot.demo.model.TRAdminButton;
import org.apache.dubbo.springboot.demo.model.entity.CreditCards;
import org.apache.dubbo.springboot.demo.model.entity.DepositCards;
import org.apache.dubbo.springboot.demo.model.entity.TransactionRecords;
import org.apache.dubbo.springboot.demo.model.entity.UserInfos;
import org.apache.dubbo.springboot.demo.provider.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

    @Autowired
    private CreditCardsDao creditCardsDao;

    @Override
    public List<UserInfos> queryAllUserInfo() {
        return this.userInfosDao.queryAllUserInfo();
    }

    @Override
    public List<DepositCards> queryAllDeCardsInfo() {
        return this.depositCardsDao.queryAllCardInfo();
    }

    @Override
    public List<CreditCards> queryAllCreCardsInfo() {
        return this.creditCardsDao.queryAllCardInfo();
    }

    @Override
    public List<DepositCards> queryAllDeCardsInfoByUserId(long userId) {
        return this.depositCardsDao.queryAllCardInfo().stream()
                .filter(card -> card.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditCards> queryAllCreCardsInfoByUserId(long userId) {
        return this.creditCardsDao.queryAllCardInfo().stream()
                .filter(card -> card.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionRecords> queryAllRecordInfo() {
        return this.transactionRecordsDao.queryAllRecords();
    }

    @Override
    public TRAdminButton modStatusByUserId(TPAdminButton tpAdminButton) {
        int var1 = this.userInfosDao.modStatusByUserId(tpAdminButton.getStatus(), tpAdminButton.getUserId());
        boolean var2 = (var1 != 0);
        String returnString = null;
        if (var2) {
            returnString = "成功设置用户状态为" + tpAdminButton.getStatus();
        } else {
            returnString = "用户已处于" + tpAdminButton.getStatus() + "状态";
        }
        return new TRAdminButton(var2, returnString);
    }

    @Override
    public TRAdminButton deleteUserByUserId(TPAdminButton tpAdminButton) {
        return new TRAdminButton(userInfosDao.deleteUserByUserId(tpAdminButton.getUserId()) != 0, "删除成功！");
    }
}
