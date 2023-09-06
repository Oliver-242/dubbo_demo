package org.apache.dubbo.springboot.demo.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author caijizhou
 * @date 2023/09/05 17:50
 */
@Data
public class TPRegister implements Serializable {
    @Serial
    private static final long serialVersionUID = 4395436335232741395L;

    private String userName;

    private String password;

    /**
     * 昵称可为空
     */
    private String nickName;
}
