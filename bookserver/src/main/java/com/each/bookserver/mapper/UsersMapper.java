package com.each.bookserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.each.bookserver.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Each
 * @since 2019-08-30
 */
@Mapper
@Component
public interface UsersMapper extends BaseMapper<Users> {
    /**
     *分页条件查询
     * current 当前页
     * size    每页显示的条数
     * total    总页数
     */
    IPage<Users> selectPageUsers(Page page, @Param("users") Users users);
}
