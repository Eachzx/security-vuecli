package com.each.bookserver.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.each.bookserver.dto.Data;
import com.each.bookserver.entity.*;
import com.each.bookserver.mapper.RoleMapper;
import com.each.bookserver.util.GetPostRequestContentUtil;
import com.each.bookserver.util.JwtUtil;
import com.each.bookserver.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ProjectName: bookserver
 * @Package: com.each.bookserver.filter
 * @Description: security中处理关于登陆的操作
 * @Author: zhangxu
 * @CreateDate: 2019/7/31 11:03
 * <p>
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private String head;

    private String tokenHeader;

    private RoleMapper roleMapper;

    /**
     * 接收并解析用户登陆信息  /login,必须使用/login，和post方法才会进入此filter
     * 如果身份验证过程失败，就抛出一个AuthenticationException
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //从request中获取username和password，并封装成user
        String body =  new GetPostRequestContentUtil().getRequestBody(request);
        User user = (User) ObjectMapperUtil.readValue(body, User.class);
        if (user == null){
            log.error("解析出错");
            return null;
        }
        String userName = user.getUserName();
        String password = user.getPassword();
        log.info("用户(登录名)：{} 正在进行登录验证。。。密码：{}",userName,password);
        //将用户密码生成token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,password);
        //提交给自定义的provider组件进行身份验证和授权
        Authentication authentication = authenticationManager.authenticate(token);
        return authentication;
    }

    /**
     * 验证成功后,此方法会被调用,在此方法中生成token,并返回给客户端
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //设置安全上下文。在当前的线程中，任何一处都可以通过SecurityContextHolder来获取当前用户认证成功的Authentication对象
        SecurityContextHolder.getContext().setAuthentication(authResult);
        MyUserDetails userDetails = (MyUserDetails) authResult.getPrincipal();
        //使用JWT快速生成token
        String token = JwtUtil.setClaim(userDetails.getUsername(),true,30*60*1000);
        //根据当前用户的权限可以获取当前用户可以查看的父菜单以及子菜单。
        // (这里在UserDetailsService中由于级联查询，该用户下的所有信息已经查出)
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<Menu> menus =new ArrayList<>();
        for (GrantedAuthority authority : authorities){
            String roleName = authority.getAuthority();
            QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("name",roleName);
            Role role = roleMapper.selectOne(queryWrapper);
            if (role.getMenus()!=null||role.getMenus().size()>0){
                menus.removeAll(role.getMenus());
                menus.addAll(role.getMenus());
            }
        }
        response.setHeader(tokenHeader,head+token);
        response.setCharacterEncoding("utf-8");
        Data data=new Data();
        data.setName(userDetails.getName());
        data.setUser_id(userDetails.getId());
        List<String> list=new ArrayList<>();
        for (Role role:userDetails.getRoles()) {
            list.add(role.getName().substring(5));
            data.setAccess(list);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(token).insert(0,head+" ");
        data.setToken(sb.toString());
        data.setAvatar(userDetails.getUserface());
        data.setRouters(menus);
//        response.getWriter().write(ObjectMapperUtil.writeAsString(menus));
        response.getWriter().write(ObjectMapperUtil.writeAsString(data));

    }

    public MyLoginFilter(AuthenticationManager authenticationManager, String head, String tokenHeader, RoleMapper roleMapper) {
        this.authenticationManager = authenticationManager;
        this.head = head;
        this.tokenHeader = tokenHeader;
        this.roleMapper = roleMapper;
    }
}
