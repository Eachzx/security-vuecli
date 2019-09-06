package com.each.bookserver.mapper;

import com.each.bookserver.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Each
 * @since 2019-07-29
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
