package com.each.bookserver.mapper;

import com.each.bookserver.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRoleListByUserId(@Param("id") Integer id);

}
