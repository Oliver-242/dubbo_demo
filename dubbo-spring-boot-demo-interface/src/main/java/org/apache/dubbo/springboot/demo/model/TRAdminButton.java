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
public class TRAdminButton implements Serializable {
    @Serial
    private static final long serialVersionUID = 837554752892288182L;

    private boolean status;

    private String returnString;
}
