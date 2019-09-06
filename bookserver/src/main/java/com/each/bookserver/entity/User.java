package com.each.bookserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@TableName(value = "user",resultMap = "BaseResultMap")
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住宅电话
     */
    private String telephone;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String userface;

    /**
     * 是否锁定
     */
    private Boolean enabled;

    /**
     * 备注
     */
    private String remark;


    @TableField(exist = false)
    private List<Role> roles;




    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.phone = user.phone;
        this.telephone = user.telephone;
        this.address = user.address;
        this.userName = user.userName;
        this.password = user.password;
        this.userface = user.userface;
        this.enabled = user.enabled;
        this.remark = user.remark;
        this.roles = user.roles;
    }
}
