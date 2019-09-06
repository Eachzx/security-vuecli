package com.each.bookserver.config;

import com.each.bookserver.filter.*;
import com.each.bookserver.mapper.RoleMapper;
import com.each.bookserver.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.config
 * @Description: security配置类
 * @Author: zhangxu
 * @CreateDate: 2019/7/30 17:16
 * Copyright: Copyright (c) 2019
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.head}")
    private String head;

    @Value("${jwt.expired}")
    private boolean expired;

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.permitUris}")
    private String permitUris;

    @Autowired
    RoleMapper roleMapper;

    @Bean
    public UserDetailsService myUserDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new MyAuthenticationProvider(myUserDetailsService(),passwordEncoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//禁用session
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
//                .antMatchers(permitUris.split(",")).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler())//处理用户登录后的无权访问
                .and()
                .addFilterBefore(new MyExceptionHandleFilter(), LogoutFilter.class)
                .addFilter(new MyLogoutFilter(new MyLogoutSuccessHandler(),new MyLogoutHandler(tokenHeader,head),"/logout"))
                .addFilter(new MyLoginFilter(authenticationManager(),head,tokenHeader,roleMapper))
                .addFilter(new MyAuthenticationFilter(authenticationManager(),tokenHeader,head,myUserDetailsService(),permitUris));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
