package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.annotation.EntranceLog;
import org.apache.dubbo.springboot.demo.enums.RecordTypeEnum;
import org.apache.dubbo.springboot.demo.model.dto.SaveRecordDto;
import org.apache.dubbo.springboot.demo.provider.DemoService;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.apache.dubbo.springboot.demo.provider.RecordService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * @author caijizhou
 * @date 2023/08/30 14:00
 */
@Controller
@Slf4j
public class MyController {

    @DubboReference(group = "group1", version = "1.0.0")
    private DemoService demoService;

    @DubboReference(group = "group1", version = "1.0.0")
    private RecordService recordService;

    @GetMapping(value = "/")
    public String home() {
        return "syslogin";
    }

    @GetMapping("/transfer")
    public String dispTransfer() {
        return "transaction";
    }

    @PostMapping("/transfer")
    @EntranceLog
    public String transfer(@RequestParam("cardid1") String sender,
                           @RequestParam("cardid2") String receiver,
                           @RequestParam("money") String money,
                           Model model) throws Exception {
        log.info("调用transfer(controller)");
        if(sender==null || receiver==null || money==null) {
            model.addAttribute("result", "输入不能为空！");
            model.addAttribute("function", RecordTypeEnum.TRANSFER.getDesc());
            return "transaction";
        }
        long money1 = Long.parseLong(money);
        TParam tParam = new TParam(sender, receiver, money1);
        TReturn tReturn = demoService.transfer(tParam);
        log.info("调用结果：{}", tReturn);
        SaveRecordDto<TParam, TReturn> saveRecordDto =
                new SaveRecordDto<>(tParam, tReturn, RecordTypeEnum.TRANSFER.getDesc(), 1);
        recordService.saveRecord(saveRecordDto);

        model.addAttribute("result", tReturn.getReturnString());
        model.addAttribute("function", RecordTypeEnum.TRANSFER.getDesc());
        return "transaction";
    }

    @PostMapping("/query")
    @EntranceLog
    public String query(HttpServletRequest httpServletRequest, @RequestParam("cardid") String cardId, @NotNull Model model) throws Exception {
        log.info("调用query(controller)");
        TParam tParam = new TParam(cardId);
        CompletableFuture<TReturn> tReturn = demoService.inquireAsync(tParam);
        var res = tReturn.get();
        log.info("调用结果：{}", tReturn);
        SaveRecordDto<TParam, CompletableFuture<TReturn>> saveRecordDto =
                new SaveRecordDto<>(tParam, tReturn, "query", (long) httpServletRequest.getSession(false).getAttribute("userId"));
        recordService.saveRecordServiceAsync(saveRecordDto);

        model.addAttribute("result", res.getReturnString());
        model.addAttribute("function", RecordTypeEnum.QUERY.getDesc());
        return "transaction";
    }

    @PostMapping("/withdraw")
    @EntranceLog
    public String withdraw(@RequestParam("cardid") String cardId,
                           @RequestParam("money") long money,
                           @NotNull Model model) throws Exception {
        log.info("调用withdraw(controller)");
        TParam tParam = new TParam(cardId, money);
        TReturn tReturn = demoService.withdraw(tParam);
        log.info("调用结果：{}", tReturn);
        SaveRecordDto<TParam, TReturn> saveRecordDto =
                new SaveRecordDto<>(tParam, tReturn, RecordTypeEnum.WITHDRAW.getDesc(), 1);
        recordService.saveRecord(saveRecordDto);

        model.addAttribute("result", tReturn.getReturnString());
        model.addAttribute("function", RecordTypeEnum.WITHDRAW.getDesc());
        return "transaction";
    }

    @PostMapping("/deposit")
    @EntranceLog
    public String deposit(@RequestParam("cardid") String cardId,
                          @RequestParam("money") long money,
                          @NotNull Model model) throws Exception {
        log.info("调用deposit(controller)");
        TParam tParam = new TParam(cardId, money);
        TReturn tReturn = demoService.deposit(tParam);
        log.info("调用结果：{}", tReturn);
        SaveRecordDto<TParam, TReturn> saveRecordDto =
                new SaveRecordDto<>(tParam, tReturn, RecordTypeEnum.DEPOSIT.getDesc(), 1);
        recordService.saveRecord(saveRecordDto);

        model.addAttribute("result", tReturn.getReturnString());
        model.addAttribute("function", RecordTypeEnum.DEPOSIT.getDesc());
        return "transaction";
    }
}
