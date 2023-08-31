package org.apache.dubbo.springboot.demo.provider;

import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;

/**
 * @author caijizhou
 * @date 2023/08/31 9:40
 */
public interface RecordService {
    void saveRecord(SaveRecordDto saveRecordDto);
}
