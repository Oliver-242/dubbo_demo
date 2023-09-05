package org.apache.dubbo.springboot.demo.provider.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.springboot.demo.model.TPRegister;
import org.apache.dubbo.springboot.demo.model.TRRegister;
import org.apache.dubbo.springboot.demo.provider.RegisterService;
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
    @Override
    public TRRegister createUser(TPRegister tpRegister) {
        return null;
    }
}
