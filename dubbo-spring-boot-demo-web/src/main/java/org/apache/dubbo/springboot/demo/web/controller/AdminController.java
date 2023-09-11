package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.model.dao.DepositCards;
import org.apache.dubbo.springboot.demo.model.dao.TransactionRecords;
import org.apache.dubbo.springboot.demo.model.dao.UserInfos;
import org.apache.dubbo.springboot.demo.provider.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author caijizhou
 * @date 2023/09/07 10:20
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @DubboReference(group = "group1", version = "1.0.0")
    private AdminService adminService;

    @GetMapping(value = "/home")
    public String doGetAdminHome(Model model) {
        List<UserInfos> userInfosList = this.adminService.queryAllUserInfo();
        List<DepositCards> depositCardsList = this.adminService.queryAllDeCardsInfo();
        List<TransactionRecords> transactionRecordsList = this.adminService.queryAllRecordInfo();
        model.addAttribute("infos", userInfosList);
        model.addAttribute("cards", depositCardsList);
        model.addAttribute("card1s", transactionRecordsList);
        return "admin";
    }

    @PostMapping(value = "/freeze-user")
    public ResponseEntity<String> doPostFreezeUser(@RequestParam("userId") String userId) {
        return new ResponseEntity<>("User frozen successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<String> doPostDeleteUser(@RequestParam("userId") String userId) {
        return new ResponseEntity<>("User delete successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/restore-user")
    public ResponseEntity<String> doPostRestoreUser(@RequestParam("userId") String userId) {
        return new ResponseEntity<>("User restore successfully", HttpStatus.OK);
    }
}
