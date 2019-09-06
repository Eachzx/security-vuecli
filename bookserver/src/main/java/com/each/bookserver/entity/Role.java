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
@TableName(value = "role",resultMap = "BaseResultMap")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 英文名字
     */
    private String name;

    /**
     * 中文名字
     */
    @TableField("No")
    private String No;

    /**
     *菜单以及权限集合
     */
    @TableField(exist = false)
    private List<Menu> menus;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String NO = "No";

}
