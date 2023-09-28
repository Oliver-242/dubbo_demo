package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.TPAdminButton;
import org.apache.dubbo.springboot.demo.model.TRAdminButton;
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

    List<TransactionRecords> queryAllRecordInfo();

    TRAdminButton modStatusByUserId(TPAdminButton tpAdminButton);
}
