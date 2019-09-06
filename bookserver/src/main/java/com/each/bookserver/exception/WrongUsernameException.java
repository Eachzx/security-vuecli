package com.each.bookserver.exception;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.exception
 * @Description: 登陆时错误
 * @Author: zhangxu
 * @CreateDate: 2019/7/30 21:52
 * @UpdateUser: zhangxu
 * @UpdateDate: 2019/7/30 21:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
public class WrongUsernameException extends RuntimeException{

    public WrongUsernameException(String message) {
        super(message);
    }

    public WrongUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
