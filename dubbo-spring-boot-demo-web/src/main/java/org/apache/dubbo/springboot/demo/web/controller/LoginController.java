package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

/**
 * @author caijizhou
 * @date 2023/09/05 14:00
 */
@Controller
@Slf4j
public class LoginController {
    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String passWord, Model model) {
        if (!StringUtils.isEmpty(userName) && "123123".equals(passWord)) {
            return "transaction";
        } else {
            model.addAttribute("msg", "用户名或者密码错误!");
            return "syslogin";

        }
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/sysregister")
    public String sysRegister(@RequestParam("name") String userName, @RequestParam("password") String password,
                              @RequestParam("nickname") String nickName, Model model) {
        return "transaction";
    }
}