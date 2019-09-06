package com.each.bookserver.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.util
 * @Description: 读取用户请求时，附带的请求信息
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 11:08
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class GetPostRequestContentUtil {
    public static String getRequestBody(HttpServletRequest request){
        try {
            //获取requestBody中的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String wholeStr = "";
            String str;
            //一行一行的读取body体里面的内容；
            while ((str = reader.readLine())!= null){
                wholeStr += str;
            }
            return wholeStr;
        }catch (IOException e){
            log.error("获取request流失败");
            return null;
        }
    }
}
