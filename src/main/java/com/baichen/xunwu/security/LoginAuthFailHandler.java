package com.baichen.xunwu.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date: 2019-12-01 19:54
 * @Author: baichen
 * @Description 登录验证失败处理器
 */
public class LoginAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private final LoginUrlEntryPoint urlEntryPoint;

    public LoginAuthFailHandler(LoginUrlEntryPoint urlEntryPoint) { // 生成构造器
        this.urlEntryPoint = urlEntryPoint;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String targetUrl = this.urlEntryPoint.determineUrlToUseForThisRequest(request, response, exception);

        targetUrl += "?" + exception.getMessage();  // 拼接参数异常信息
        super.setDefaultFailureUrl(targetUrl);
        super.onAuthenticationFailure(request, response, exception);
    }
}
