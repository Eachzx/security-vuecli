package com.each.bookserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.each.bookserver.entity.Menu;
import com.each.bookserver.entity.Role;
import com.each.bookserver.entity.User;
import com.each.bookserver.entity.Users;
import com.each.bookserver.mapper.MenuMapper;
import com.each.bookserver.mapper.RoleMapper;
import com.each.bookserver.mapper.UserMapper;
import com.each.bookserver.mapper.UsersMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookserverApplicationTests {


    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    UsersMapper usersMapper;
    @Test
    public void contextLoads() {
//   Role roles=roleMapper.selectById(1);
     //User user = userMapper.selectById(1);
   //log.info("ssss:{}",JSON.toJSON(user));
        User user=new User();

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String pas=bCryptPasswordEncoder.encode("123456");
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        user.setPassword(pas);
       wrapper.eq("id",1);
            userMapper.update(user,wrapper);
    }
    @Test
    public void test1(){
        String a = "hello";
        StringBuffer sb = new StringBuffer();
        sb.append(a).insert(0,"aaa ");
        log.info(sb.toString());
    }

    @Test
    public void users(){
//        List<Users> usersList=usersMapper.selectList(null);
//        log.info("用户人员表:{}",usersList);

        Users users1=new Users();
        users1.setSex(1);
        Page<Users> page=new Page<Users>();
        page.setSize(5);
        page.setCurrent(1);
        IPage<Users> iPage=usersMapper.selectPageUsers(page,users1);
//        log.info("分页结果为:{},{}",iPage.getRecords(),iPage.get());
        System.out.println("woshisb");

    }

}
