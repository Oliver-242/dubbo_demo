package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caijizhou
 * @date 2023/09/07 10:20
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @GetMapping(value = "/home")
    public String doGetAdminHome() {
        return "admin";
    }
}
