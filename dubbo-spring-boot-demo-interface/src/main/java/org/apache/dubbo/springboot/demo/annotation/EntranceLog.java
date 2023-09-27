package org.apache.dubbo.springboot.demo.annotation;

import java.lang.annotation.*;

/**
 * Log日志切面
 * 出入口参数及处理时间
 *
 * @author caijizhou
 * @date 2023/09/27 10:40
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EntranceLog {
}
