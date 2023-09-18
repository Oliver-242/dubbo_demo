package org.apache.dubbo.springboot.demo.test;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.provider.DemoService;
import org.apache.dubbo.springboot.demo.web.MyControllerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = MyControllerApplication.class)
public class MyControllerApplicationTests {
    @DubboReference(group = "group1", version = "1.0.0")
    private DemoService demoService;
    @Test
    public void myTest() throws Exception {
        System.out.println(demoService.inquire(new TParam("1231231231231")));
    }
}
