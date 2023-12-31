package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.annotation.EntranceLog;
import org.apache.dubbo.springboot.demo.enums.BusinessStatusEnum;
import org.apache.dubbo.springboot.demo.enums.RecordTypeEnum;
import org.apache.dubbo.springboot.demo.model.TPAdminButton;
import org.apache.dubbo.springboot.demo.model.TRAdminButton;
import org.apache.dubbo.springboot.demo.model.entity.CreditCards;
import org.apache.dubbo.springboot.demo.model.entity.DepositCards;
import org.apache.dubbo.springboot.demo.model.entity.TransactionRecords;
import org.apache.dubbo.springboot.demo.model.entity.UserInfos;
import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;
import org.apache.dubbo.springboot.demo.provider.AdminService;
import org.apache.dubbo.springboot.demo.provider.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    @DubboReference(group = "group1", version = "1.0.0")
    private RecordService recordService;

    @GetMapping(value = "/home")
    @EntranceLog
    public String doGetAdminHome(Model model) {
        List<UserInfos> userInfosList = this.adminService.queryAllUserInfo();
        List<DepositCards> depositCardsList = this.adminService.queryAllDeCardsInfo();
        List<CreditCards> creditCardsList = this.adminService.queryAllCreCardsInfo();
        List<TransactionRecords> transactionRecordsList = this.adminService.queryAllRecordInfo();
        model.addAttribute("infos", userInfosList);
        model.addAttribute("cards", depositCardsList);
        model.addAttribute("card2s", creditCardsList);
        model.addAttribute("records", transactionRecordsList);
        return "admin";
    }

    @PostMapping(value = "/freeze-user")
    @EntranceLog
    public ResponseEntity<String> doPostFreezeUser(HttpServletRequest httpServletRequest,
                                                   @RequestParam("userId") String userId) throws Exception {
        log.info("调用freezeUser(controller)");
        TPAdminButton tpAdminButton =
                new TPAdminButton(BusinessStatusEnum.FROZEN.getStatus(), Long.parseLong(userId));
        TRAdminButton trAdminButton = adminService.modStatusByUserId(tpAdminButton);
        SaveRecordDto<TPAdminButton, TRAdminButton> saveRecordDto =
                new SaveRecordDto<>(tpAdminButton, trAdminButton, RecordTypeEnum.FREEZEUSER.getDesc(), (long) httpServletRequest.getSession().getAttribute("userId"));
        recordService.saveRecordButton(saveRecordDto);

        return new ResponseEntity<>("User freeze successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete-user")
    @EntranceLog
    public ResponseEntity<String> doPostDeleteUser(HttpServletRequest httpServletRequest,
                                                   @RequestParam("userId") String userId) throws Exception {
        log.info("调用deleteUser(controller)");
        TPAdminButton tpAdminButton =
                new TPAdminButton(null, Long.parseLong(userId));
        TRAdminButton trAdminButton = adminService.deleteUserByUserId(tpAdminButton);
        SaveRecordDto<TPAdminButton, TRAdminButton> saveRecordDto =
                new SaveRecordDto<>(tpAdminButton, trAdminButton, RecordTypeEnum.DELETEUSER.getDesc(), (long) httpServletRequest.getSession().getAttribute("userId"));
        recordService.saveRecordButton(saveRecordDto);

        return new ResponseEntity<>("User delete successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/restore-user")
    @EntranceLog
    public ResponseEntity<String> doPostRestoreUser(HttpServletRequest httpServletRequest,
                                                    @RequestParam("userId") String userId) throws Exception {
        log.info("调用restoreUser(controller)");
        TPAdminButton tpAdminButton =
                new TPAdminButton(BusinessStatusEnum.ACTIVE.getStatus(), Long.parseLong(userId));
        TRAdminButton trAdminButton = adminService.modStatusByUserId(tpAdminButton);
        SaveRecordDto<TPAdminButton, TRAdminButton> saveRecordDto =
                new SaveRecordDto<>(tpAdminButton, trAdminButton, RecordTypeEnum.RESTOREUSER.getDesc(), (long) httpServletRequest.getSession().getAttribute("userId"));
        recordService.saveRecordButton(saveRecordDto);

        return new ResponseEntity<>("User restore successfully", HttpStatus.OK);
    }
}
