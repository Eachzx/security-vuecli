package com.each.bookserver.filter;

import com.each.bookserver.enmu.AuthErrorEnum;
import com.each.bookserver.exception.IllegalTokenAuthenticationException;
import com.each.bookserver.exception.NoneTokenException;
import com.each.bookserver.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.filter
 * @Description: java类作用描述
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 13:02
 * @UpdateUser: zhangxu
 * @UpdateDate: 2019/7/31 13:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MyLogoutHandler implements LogoutHandler {
    private String tokenHeader;

    private String head;

    public MyLogoutHandler(String tokenHeader, String head) {
        this.tokenHeader = tokenHeader;
        this.head = head;
    }
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("执行登出操作...");
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            log.warn("请先登录");
            throw new NoneTokenException(AuthErrorEnum.TOKEN_NEEDED.getMessage());
        }
        if (!token.startsWith(head)){
            log.warn("token信息不合法");
            throw new IllegalTokenAuthenticationException(AuthErrorEnum.AUTH_HEADER_ERROR.getMessage());
        }
        Claims claims = JwtUtil.getClaim(token.substring(head.length()));
        if (claims == null){
            request.setAttribute("userName",null);
        }else {
            String userName = claims.getSubject();
            request.setAttribute("userName",userName);
        }
    }
}
