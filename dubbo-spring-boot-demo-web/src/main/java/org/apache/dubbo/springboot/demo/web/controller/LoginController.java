package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.model.TPRegister;
import org.apache.dubbo.springboot.demo.model.TRRegister;
import org.apache.dubbo.springboot.demo.provider.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author caijizhou
 * @date 2023/09/05 14:00
 */
@Controller
@Slf4j
public class LoginController {
    @DubboReference(group = "group1", version = "1.0.0")
    private RegisterService registerService;

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("userType") String userType, Model model) {
        TPRegister tpRegister = new TPRegister();
        tpRegister.setUserName(userName);
        tpRegister.setPassword(password);
        tpRegister.setNickName(userType);
        if (this.registerService.loginVerify(tpRegister).isStatus()) {
            return "transaction";
        } else {
            model.addAttribute("msg", "登录失败!");
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
        TPRegister tpRegister = new TPRegister();
        tpRegister.setUserName(userName);
        tpRegister.setPassword(password);
        tpRegister.setNickName(nickName);
        TRRegister trRegister = registerService.createUser(tpRegister);
        model.addAttribute("result", trRegister.getReturnString());
        return "transaction";
    }
}