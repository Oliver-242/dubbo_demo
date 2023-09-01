package org.apache.dubbo.springboot.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

/**
 * @author caijizhou
 * @date 2023/08/31 10:30
 */
@Data
public class SaveRecordDto<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -8283476793301712821L;

    private TParam tParam;

    private T tReturn;

    private String typeId;

    private long userId;

    public SaveRecordDto(TParam tParam, T tReturn, String typeId, long userId) {
        this.tParam = tParam;
        this.tReturn = tReturn;
        this.typeId = typeId;
        this.userId = userId;
    }
}
