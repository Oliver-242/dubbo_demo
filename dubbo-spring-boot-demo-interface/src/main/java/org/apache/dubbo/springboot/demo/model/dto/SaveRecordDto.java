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
public class SaveRecordDto<T1, T2> implements Serializable {
    @Serial
    private static final long serialVersionUID = -8283476793301712821L;

    private T1 tParam;

    private T2 tReturn;

    private String typeId;

    /**
     * 平时表示操作的执行者
     * <p><strong>如果是"id1(id2)"的形式，那么id1是执行人(通常为admin)
     * id2则为受影响人</strong></p>
     */
    private long userId;

    public SaveRecordDto(T1 tParam, T2 tReturn, String typeId, long userId) {
        this.tParam = tParam;
        this.tReturn = tReturn;
        this.typeId = typeId;
        this.userId = userId;
    }
}
