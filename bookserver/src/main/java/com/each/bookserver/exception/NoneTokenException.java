package com.each.bookserver.exception;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.exception
 * @Description: java类作用描述
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 13:04
 * @UpdateUser: zhangxu
 * @UpdateDate: 2019/7/31 13:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
public class NoneTokenException extends RuntimeException{

    public NoneTokenException(String msg){
        super(msg);
    }

    public NoneTokenException(String msg, Throwable t){
        super(msg,t);
    }
}
