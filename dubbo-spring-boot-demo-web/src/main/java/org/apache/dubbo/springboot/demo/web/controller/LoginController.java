package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.enums.RecordTypeEnum;
import org.apache.dubbo.springboot.demo.enums.UserTypeEnum;
import org.apache.dubbo.springboot.demo.model.TPRegister;
import org.apache.dubbo.springboot.demo.model.TRRegister;
import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;
import org.apache.dubbo.springboot.demo.provider.RecordService;
import org.apache.dubbo.springboot.demo.provider.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

/**
 * @author caijizhou
 * @date 2023/09/05 14:00
 */
@Controller
@Slf4j
public class LoginController {
    @DubboReference(group = "group1", version = "1.0.0")
    private RegisterService registerService;

    @DubboReference(group = "group1", version = "1.0.0")
    private RecordService recordService;

    @GetMapping(value = "/dogetlogin")
    private String doGetLogin() {
        return "syslogin";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("phonenumber") String phoneNumber,
                        @RequestParam("password") String password,
                        @RequestParam("userType") String userType, Model model) throws Exception {
        log.info("调用login(controller)");
        TPRegister tpRegister = new TPRegister();
        tpRegister.setPhoneNumber(phoneNumber);
        tpRegister.setPassword(password);
        tpRegister.setUserType(userType);
        TRRegister trRegister = this.registerService.loginVerify(tpRegister);
        log.info("调用结果：{}", trRegister);
        SaveRecordDto<TPRegister, TRRegister> saveRecordDto =
            new SaveRecordDto<>(tpRegister, trRegister, RecordTypeEnum.LOGIN.getDesc(), 1);
        recordService.saveRecordRegLogAsync(saveRecordDto);
        if (trRegister.isStatus()) {
            model.addAttribute("msg", "");
            if(Objects.equals(userType, UserTypeEnum.USER.name())) {
                return "redirect:/transfer";
            } else {
                return "redirect:/admin/home";
            }
        } else {
            model.addAttribute("msg", "登录失败!");
            model.addAttribute("phonenumber", phoneNumber);
            model.addAttribute("none", "");
            return "syslogin";

        }
    }

    @GetMapping(value = "/register")
    public String doGetRegister() {
        return "register";
    }

    @PostMapping(value = "/sysregister")
    public String sysRegister(@RequestParam("name") String userName,
                              @RequestParam("password") String password,
                              @RequestParam("nickname") String nickName,
                              @RequestParam("phonenumber") String phoneNumber, Model model) throws Exception {
        log.info("调用sysRegister(controller)");
        TPRegister tpRegister = new TPRegister();
        tpRegister.setUserName(userName);
        tpRegister.setPhoneNumber(phoneNumber);
        tpRegister.setPassword(password);
        tpRegister.setNickName(nickName);
        TRRegister trRegister = registerService.createUser(tpRegister);
        log.info("调用结果：{}", trRegister);
        SaveRecordDto<TPRegister, TRRegister> saveRecordDto =
                new SaveRecordDto<>(tpRegister, trRegister, RecordTypeEnum.REGISTER.getDesc(), 1);
        recordService.saveRecordRegLogAsync(saveRecordDto);
        model.addAttribute("result", trRegister.getReturnString());
        return "regsuccess";
    }
}