package com.baichen.xunwu.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @Date: 2019-12-1 13：44
 * @Author: baichen
 * @Description 基于角色的登录入口控制器
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

	private PathMatcher pathMatcher = new AntPathMatcher(); // Spring自带的工具类
    
	private final Map<String, String> authEntryPointMap;    // 入口点map,key为请求URI，value是对应的入口点

    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        authEntryPointMap = new HashMap<>();

        // 普通用户登录入口映射
        authEntryPointMap.put("/user/**", "/user/login");
        // 管理员登录入口映射
        authEntryPointMap.put("/admin/**", "/admin/login");
    }
    /**
     * 根据请求跳转到指定的页面，父类是默认使用loginFormUrl
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
                                                     AuthenticationException exception) {
        // 原先跳转的URI
        String uri = request.getRequestURI().replace(request.getContextPath(), "");

        // 遍历每一个请求的URI
        for (Map.Entry<String, String> authEntry : this.authEntryPointMap.entrySet()) {
            if (this.pathMatcher.match(authEntry.getKey(), uri)) {//匹配到URI则跳转到对应的入口点
                return authEntry.getValue();
            }
        }
        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}	
