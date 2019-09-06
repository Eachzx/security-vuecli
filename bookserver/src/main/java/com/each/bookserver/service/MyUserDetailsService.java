package com.each.bookserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.each.bookserver.enmu.AuthErrorEnum;
import com.each.bookserver.entity.MyUserDetails;
import com.each.bookserver.entity.User;
import com.each.bookserver.exception.WrongUsernameException;
import com.each.bookserver.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.service
 * @Description: java类作用描述
 * @Author: zhangxu
 * @CreateDate: 2019/7/30 20:48
 * @UpdateUser: zhangxu
 * @UpdateDate: 2019/7/30 20:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userName",s);
        User user = userMapper.selectOne(queryWrapper);
        if (user!=null || user.getRoles().size()>0) {
            UserDetails userDetails = new MyUserDetails(user);
            return userDetails;
        }else {
            log.error("用户不存在");
            throw new WrongUsernameException(AuthErrorEnum.LOGIN_NAME_ERROR.getMessage());
        }

    }
}
