package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.TPAdminButton;
import org.apache.dubbo.springboot.demo.model.TRAdminButton;
import org.apache.dubbo.springboot.demo.model.entity.CreditCards;
import org.apache.dubbo.springboot.demo.model.entity.DepositCards;
import org.apache.dubbo.springboot.demo.model.entity.TransactionRecords;
import org.apache.dubbo.springboot.demo.model.entity.UserInfos;

import java.util.List;

/**
 * @author caijizhou
 * @date 2023/09/08 10:20
 */
public interface AdminService {
    List<UserInfos> queryAllUserInfo();

    List<DepositCards> queryAllDeCardsInfo();

    List<CreditCards> queryAllCreCardsInfo();

    List<DepositCards> queryAllDeCardsInfoByUserId(long userId);

    List<CreditCards> queryAllCreCardsInfoByUserId(long userId);

    List<TransactionRecords> queryAllRecordInfo();

    TRAdminButton modStatusByUserId(TPAdminButton tpAdminButton);

    TRAdminButton deleteUserByUserId(TPAdminButton tpAdminButton);
}
