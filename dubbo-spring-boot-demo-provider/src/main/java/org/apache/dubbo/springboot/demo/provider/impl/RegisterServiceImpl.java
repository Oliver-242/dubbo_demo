package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.mapper.UserInfosDao;
import org.apache.dubbo.springboot.demo.model.TPRegister;
import org.apache.dubbo.springboot.demo.model.TRRegister;
import org.apache.dubbo.springboot.demo.model.dao.UserInfos;
import org.apache.dubbo.springboot.demo.provider.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<UserInfos> userInfosList = this.userInfosDao.queryAllByUserName(tpRegister.getPhoneNumber());
        for(UserInfos info: userInfosList) {
            if(tpRegister.getPassword().equals(info.getPassword()) &&
                    tpRegister.getUserType().equals(info.getIdentification())) {
                return new TRRegister(true, "登录成功！");
            }
        }
        return new TRRegister(false, "登录失败！");
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
        return new TRRegister(status, returnString);
    }
}
