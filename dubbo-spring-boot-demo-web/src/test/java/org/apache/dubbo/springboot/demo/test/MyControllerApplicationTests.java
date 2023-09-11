package org.apache.dubbo.springboot.demo.test;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.provider.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest()
public class MyControllerApplicationTests {
    @DubboReference(group = "group1", version = "1.0.0")
    private DemoService demoService;
    @Test
    public void myTest() throws Exception {
        System.out.println(demoService.inquire(new TParam("1231231231231")));
    }
}
