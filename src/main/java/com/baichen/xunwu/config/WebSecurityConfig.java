package com.baichen.xunwu.config;

import com.baichen.xunwu.security.AuthProvider;
import com.baichen.xunwu.security.LoginAuthFailHandler;
import com.baichen.xunwu.security.LoginUrlEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @Date: 2019-11-27 22:25
 * @Author: baichen
 * @Description Spring Security配置类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)   // 要加上后面这个属性，因为Spring Security默认是不能用注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HTTP权限控制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll() // 管理员登录入口
                .antMatchers("/static/**").permitAll() // 静态资源
                .antMatchers("/user/login").permitAll() // 用户登录入口
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login") // 配置角色登录处理入口
                .failureHandler(authFailHandler())
                .and()
                .logout().logoutSuccessUrl("/logout/page") //对应HomeController中对应的返回页面
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");
        // 禁用csrf及HTTP验证
        http.csrf().disable();
        //因为前端框架H-UI是基于iframe开发的，所以这里需要开启同源
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // SB2.X开始需要增加PasswordEncoder实现类，因为引用了Spring Security5.x
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
//                .withUser("admin").password("admin")
//                .roles("ADMIN").and();
        // 擦去密码
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }

    @Bean
    public LoginUrlEntryPoint urlEntryPoint() { // 以bean的形式注入
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    public LoginAuthFailHandler authFailHandler() {
        return new LoginAuthFailHandler(urlEntryPoint());
    }
}
