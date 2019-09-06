package com.each.bookserver.service.impl;

import com.each.bookserver.entity.User;
import com.each.bookserver.mapper.UserMapper;
import com.each.bookserver.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Each
 * @since 2019-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
