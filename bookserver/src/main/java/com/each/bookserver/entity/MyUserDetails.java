package com.each.bookserver.entity;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.entity
 * @Description: 存储用户和权限信息
 * @Author: zhangxu
 * @CreateDate: 2019/7/30 17:38
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 */
public class MyUserDetails extends User implements UserDetails {

    public MyUserDetails(User user) {
        super(user);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles=super.getRoles();
       if (roles!=null || roles.size()>0){
           for (Role role:roles) {
               authorities.add(new SimpleGrantedAuthority(role.getName()));
           }
       }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
