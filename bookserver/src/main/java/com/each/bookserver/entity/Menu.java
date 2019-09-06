package com.each.bookserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Each
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "menu",resultMap = "BaseResultMap")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 前端路由路径
     */
    private String path;

    /**
     * 组件位置
     */
    private String component;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 前端路由属性
     */
    private String redirect;

    /**
     * 是否锁定
     */
    private Boolean enabled;

    /**
     * 前端路由属性
     */
    private Meta meta;

    /**
     * 自关联ID，children子菜单
     */
    @TableField(exist = false)
    private List<Menu> children;


    public static final String ID = "id";

    public static final String PATH = "path";

    public static final String COMPONENT = "component";

    public static final String NAME = "name";

    public static final String HIDEINBREAD = "hideInBread";

    public static final String HIDEINMENU = "hideInMenu";

    public static final String NOTCACHE = "notCache";

    public static final String ICON = "icon";

    public static final String TITLE = "title";

    public static final String ENABLED = "enabled";

    public static final String PARENTID = "parentId";

}
