package cn.xiaowenjie.codetemplate.controllers;

import cn.xiaowenjie.codetemplate.common.beans.ResultBean;
import cn.xiaowenjie.codetemplate.common.utils.UserUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin
@Controller
@Slf4j
public class HomeController {

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        log.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
            DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<String> login(HttpSession session, String username) {
        log.info("login user:" + username);

        // TODO 只是模拟登陆
        session.setAttribute(UserUtil.KEY_USER, username);

        return new ResultBean<>(username);
    }


    @GetMapping(value = "/resttest/{key}")
    @ResponseBody
    @SneakyThrows
    public ResultBean<String> restTest(@PathVariable String key) {
        log.info("resttest, key=" + key);
        Thread.sleep(2000);
        return new ResultBean<>("input key is " + key);
    }

    /**
     * AOP测试，AOP会拦截错误并返回包装类
     *
     * @return
     */
    @GetMapping(value = "/aoptest")
    @ResponseBody
    public ResultBean<Boolean> aoptest() {
        int a = 1 / 0;
        return new ResultBean<>(true);
    }
}
