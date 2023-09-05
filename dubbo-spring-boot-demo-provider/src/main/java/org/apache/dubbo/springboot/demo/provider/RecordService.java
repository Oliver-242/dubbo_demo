package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.TRRegister;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;

import java.util.concurrent.CompletableFuture;

/**
 * @author caijizhou
 * @date 2023/08/31 9:40
 */
public interface RecordService {
    void saveRecord(SaveRecordDto<TReturn> saveRecordDto);

    void saveRecordServiceAsync(SaveRecordDto<CompletableFuture<TReturn>> saveRecordDto) throws Exception;

    void saveRecordRegLogAsync(SaveRecordDto<CompletableFuture<TRRegister>> saveRecordDto) throws Exception;
}
