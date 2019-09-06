package com.each.bookserver.filter;

import com.each.bookserver.enmu.AuthErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.filter
 * @Description: 处理用户登陆后无权访问
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 12:54
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MyAccessDeniedHandler  implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.error("当前用户没有访问该资源的权限：{}",e.getMessage());
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(AuthErrorEnum.ACCESS_DENIED.getMessage());
    }
}
