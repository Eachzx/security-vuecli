package com.each.bookserver.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.each.bookserver.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Each
 * @since 2019-08-30
 */
public interface UsersService extends IService<Users> {
    /**
     * 根据用户的姓名以及电话号码分页查询
     */
    IPage<Users> selectUsersPage(Users users, Page page);

    /**
     * 修改用户信息
     */
    void updateUsers(Users users);

    /**
     * 根据用户ID删除用户
     */
    void deleteByUsersId(Users users);

    /**
     * 添加用户
     */
    void addUsers(Users users);
}

