package com.each.bookserver.mapper;

import com.each.bookserver.entity.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据权限id查询出，父级菜单的List
     **/
    List<Menu> selectParentListByRoleId(@Param("id")Integer id);
    /**
     * 根据Id查询出子菜单list
     */
    List<Menu> selectChildrenListById(@Param("id")Integer id);

}
