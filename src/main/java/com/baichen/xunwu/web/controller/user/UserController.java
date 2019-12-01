package com.baichen.xunwu.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Date: 2019-12-01 15:52
 * @Author: baichen
 * @Description
 */
@Controller
public class UserController {
    @GetMapping("/user/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/user/center")
    public String centerPage() {
        return "user/center";
    }
}
