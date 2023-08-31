package org.apache.dubbo.springboot.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author caijizhou
 * @date 2023/08/31 10:30
 */
@Data
@AllArgsConstructor
public class SaveRecordDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8283476793301712821L;

    private TParam tParam;

    private TReturn tReturn;

    private String typeId;

    private long userId;
}
