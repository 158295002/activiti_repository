package com.evada.activiti.controller;

import com.evada.activiti.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author dingqin
 * @date 2018/1/8
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.GET)
    public String doLogin(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
//后续做校验
        //        if ("888888".equals(password) && "某人".equals(name)) {
//        }
        SessionUtil.addSessionAttribute("user", name);
        model.addAttribute("name", name);
        return "userPage";
    }

    @RequestMapping(value = "doJump", method = RequestMethod.GET)
    public String dojump(Model model) {
        String name = SessionUtil.getCurrentUser();
        model.addAttribute("name", name);
        return "userPage";
    }
}
