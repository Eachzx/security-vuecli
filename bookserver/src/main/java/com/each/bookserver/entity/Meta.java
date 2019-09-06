package com.each.bookserver.entity;

import lombok.Data;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.entity
 * @Description: 前端路由属性
 * @Author: zhangxu
 * @CreateDate: 2019/7/29 23:09
 * @UpdateUser: zhangxu
 * @UpdateDate: 2019/7/29 23:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Data
public class Meta {
    private Boolean hideInBread;

    private Boolean hideInMenu;

    private Boolean notCache;

    /**
     * 图标
     */
    private String icon;
    /**
     * 面包屑名称
     */
    private String title;

    private String beforeCloseName;

    private String href;
}
