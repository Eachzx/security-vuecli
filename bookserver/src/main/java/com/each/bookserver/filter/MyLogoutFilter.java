package com.each.bookserver.filter;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.filter
 * @Description: 登出处理
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 13:00
 * @UpdateUser: zhangxu
 * @UpdateDate: 2019/7/31 13:00
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
public class MyLogoutFilter extends LogoutFilter {
    public MyLogoutFilter(MyLogoutSuccessHandler logoutSuccessHandler, MyLogoutHandler logoutHandler,String filterProcessesUrl) {
        super(logoutSuccessHandler, logoutHandler);
        //更改默认的登出URL
//        super.setFilterProcessesUrl(filterProcessesUrl);
    }

}
