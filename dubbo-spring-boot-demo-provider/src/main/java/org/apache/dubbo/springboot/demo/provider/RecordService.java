package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.TPAdminButton;
import org.apache.dubbo.springboot.demo.model.TPRegister;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TRAdminButton;
import org.apache.dubbo.springboot.demo.model.TRRegister;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;

import java.util.concurrent.CompletableFuture;

/**
 * @author caijizhou
 * @date 2023/08/31 9:40
 */
public interface RecordService {
    void saveRecord(SaveRecordDto<TParam, TReturn> saveRecordDto) throws Exception;

    void saveRecordButton(SaveRecordDto<TPAdminButton, TRAdminButton> saveRecordDto) throws Exception;

    void saveRecordServiceAsync(SaveRecordDto<TParam, CompletableFuture<TReturn>> saveRecordDto) throws Exception;

    void saveRecordRegLogAsync(SaveRecordDto<TPRegister, TRRegister> saveRecordDto) throws Exception;
}
