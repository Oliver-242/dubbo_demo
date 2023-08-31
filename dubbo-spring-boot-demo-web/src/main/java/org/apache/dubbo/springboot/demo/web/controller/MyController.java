package org.apache.dubbo.springboot.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.DemoService;
import org.apache.dubbo.springboot.demo.model.TParam;
import org.apache.dubbo.springboot.demo.model.TReturn;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author caijizhou
 * @date 2023/08/30 14:00
 */
@Controller
@Slf4j
public class MyController {

    @DubboReference(group = "group1", version = "1.0.0")
    private DemoService demoservice;

    @GetMapping(value = "/")
    public String home(@NotNull Model model) {
        model.addAttribute("function", "transfer");
        model.addAttribute("result", "");
        return "home";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("sender") String sender,
                           @RequestParam("receiver") String receiver,
                           @RequestParam("money") String money,
                           Model model) throws Exception {
        if(sender==null || receiver==null || money==null) {
            model.addAttribute("result", "输入不能为空！");
            model.addAttribute("function", "transfer");
            return "home";
        }
        long money1 = Long.valueOf(money);
        TParam tParam = new TParam(sender, receiver, money1);
        TReturn tReturn = demoservice.transfer(tParam);

        model.addAttribute("result", tReturn.getReturnString());
        model.addAttribute("function", "transfer");
        return "home";
    }

    @PostMapping("/query")
    public String query(@RequestParam("cardid") String cardId, @NotNull Model model) throws Exception {
        TParam tParam = new TParam(cardId);
        TReturn tReturn = demoservice.inquire(tParam);

        model.addAttribute("result", tReturn.getReturnString());
        model.addAttribute("function", "query");
        return "home";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("cardid") String cardId,
                           @RequestParam("money") long money,
                           @NotNull Model model) throws Exception {
        TParam tParam = new TParam(cardId, money);
        TReturn tReturn = demoservice.withdraw(tParam);

        model.addAttribute("result", tReturn.getReturnString());
        model.addAttribute("function", "withdraw");
        return "home";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("cardid") String cardId,
                          @RequestParam("money") long money,
                          @NotNull Model model) throws Exception {
        TParam tParam = new TParam(cardId, money);
        TReturn tReturn = demoservice.deposit(tParam);

        model.addAttribute("result", tReturn.getReturnString());
        model.addAttribute("function", "deposit");
        return "home";
    }
}
