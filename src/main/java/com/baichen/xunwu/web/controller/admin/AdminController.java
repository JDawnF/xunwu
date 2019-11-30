package com.baichen.xunwu.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Date: 2019-11-30 19:39
 * @Author: baichen
 * @Description
 */
@Controller
public class AdminController {
    /**
     * 后台管理中心
     * @return
     */
    @GetMapping("/admin/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    /**
     * 管理员登录页
     * @return
     */
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }
}
