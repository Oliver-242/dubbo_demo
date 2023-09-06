package org.apache.dubbo.springboot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author caijizhou
 * @date 2023/09/05 17:50
 */
@Data
@AllArgsConstructor
public class TRRegister implements Serializable {
    @Serial
    private static final long serialVersionUID = -2371111364019479126L;

    // 1代表成功，0代表失败
    private boolean status;

    private String returnString;
}
