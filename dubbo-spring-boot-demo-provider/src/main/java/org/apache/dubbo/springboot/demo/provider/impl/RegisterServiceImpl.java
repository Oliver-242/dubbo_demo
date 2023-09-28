package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.enums.BusinessStatusEnum;
import org.apache.dubbo.springboot.demo.mapper.UserInfosDao;
import org.apache.dubbo.springboot.demo.model.TPRegister;
import org.apache.dubbo.springboot.demo.model.TRRegister;
import org.apache.dubbo.springboot.demo.model.entity.UserInfos;
import org.apache.dubbo.springboot.demo.provider.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author caijizhou
 * @date 2023/09/05 17:50
 */
@DubboService(group = "group1", version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserInfosDao userInfosDao;

    @Override
    public TRRegister loginVerify(TPRegister tpRegister) {
        UserInfos info = this.userInfosDao.queryAllByPhoneNumber(tpRegister.getPhoneNumber());
        if(tpRegister.getPassword().equals(info.getPassword()) &&
                tpRegister.getUserType().equals(info.getIdentification()) &&
                    info.getStatus().equals(BusinessStatusEnum.ACTIVE.getStatus())) {
            return new TRRegister(true, info.getUserId(), "登录成功！");
        }
        return new TRRegister(false, info.getUserId(), "登录失败！");
    }

    @Override
    public TRRegister createUser(TPRegister tpRegister) {
        UserInfos userInfos = UserInfos.builder().setTpRegister(tpRegister).build();
        boolean status = Boolean.parseBoolean(String.valueOf(userInfosDao.createUserInfo(userInfos)));
        String returnString;
        if(status) {
            returnString = "用户" + userInfos.getUserName() + "注册成功！请您牢记密码";
        } else {
            returnString = "注册失败！";
        }
        return new TRRegister(status, userInfos.getUserId(), returnString);
    }
}
