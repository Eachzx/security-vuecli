package com.each.bookserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.each.bookserver.entity.Users;
import com.each.bookserver.mapper.UsersMapper;
import com.each.bookserver.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Each
 * @since 2019-08-30
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public IPage<Users> selectUsersPage(Users users, Page page) {
        IPage<Users> iPage=usersMapper.selectPageUsers(page,users);
        return iPage;
    }

    @Override
    public void updateUsers(Users users) {
        QueryWrapper<Users> wrapper=new QueryWrapper<>();
        wrapper.eq("id",users.getId());
        usersMapper.update(users,wrapper);
    }

    @Override
    public void deleteByUsersId(Users users) {
        usersMapper.deleteById(users.getId());
    }

    @Override
    public void addUsers(Users users) {
        usersMapper.insert(users);
    }
}
