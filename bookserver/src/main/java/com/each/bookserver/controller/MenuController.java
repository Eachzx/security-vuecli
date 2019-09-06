package com.each.bookserver.controller;


import com.each.bookserver.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Each
 * @since 2019-07-29
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    @PreAuthorize("hasAuthority('Role_admin')")
    @GetMapping("/hello")
    public User sayHello(){
        User user=new User();
        user.setName("臭傻逼");
        return user;
    }

}

