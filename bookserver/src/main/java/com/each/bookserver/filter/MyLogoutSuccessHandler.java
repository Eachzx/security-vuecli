package com.each.bookserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.filter
 * @Description: 登出成功处理
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 13:01
 * @UpdateUser: zhangxu
 * @UpdateDate: 2019/7/31 13:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登出成功");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("登出成功");
    }
}