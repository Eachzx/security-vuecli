package com.each.bookserver.filter;

import com.each.bookserver.enmu.AuthErrorEnum;
import com.each.bookserver.entity.MyUserDetails;
import com.each.bookserver.exception.WrongPasswordException;
import com.each.bookserver.exception.WrongUsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.filter
 * @Description: security里实际处理认证身份
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 10:46
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MyAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //authentication,登录url提交的需要被认证的对象。只含有用户名和密码，需要根据用户名和密码来校验，并且授权。
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(userName);
        if (userDetails == null){
            log.warn("未找到用户名为的用户 :{}",userName);
            throw new WrongUsernameException(AuthErrorEnum.LOGIN_NAME_ERROR.getMessage());
        }
        /** 如果从url提交的密码到数据保存的密码没有经过加密或者编码，直接比较是否相同即可。
         * 如果在添加用户时的密码是经过加密或者编码的应该使用对应的加密算法和编码工具对密码进行编码之后再进行比较
         */
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            log.warn("Wrong password");
            throw new WrongPasswordException(AuthErrorEnum.LOGIN_PASSWORD_ERROR.getMessage());
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails,password,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //加入token认证
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * 自定义一个构造方法，方便在security配置类中使用
     */
    public MyAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
}
