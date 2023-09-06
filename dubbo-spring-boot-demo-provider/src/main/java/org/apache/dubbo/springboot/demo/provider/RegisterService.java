package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.TPRegister;
import org.apache.dubbo.springboot.demo.model.TRRegister;

/**
 * @author caijizhou
 * @date 2023/09/05 17:50
 */
public interface RegisterService {
    TRRegister loginVerify(TPRegister tpRegister);

    TRRegister createUser(TPRegister tpRegister);
}
