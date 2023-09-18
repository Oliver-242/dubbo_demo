package org.apache.dubbo.springboot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author caijizhou
 * @date 2023/09/18
 */
@Data
@AllArgsConstructor
public class TPAdminButton implements Serializable {
    @Serial
    private static final long serialVersionUID = 4454314526687291237L;

    String status;

    long userId;
}
