package com.each.bookserver.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.each.bookserver.entity.Users;
import com.each.bookserver.service.UsersService;
import com.each.bookserver.util.EasyPoiUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 用户控制层
 */
@Slf4j
@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersService service;

    /**
     * 分页查询用户信息
     * @param users
     * @param page
     * @return iPage
     */
    @PreAuthorize("hasAuthority('Role_admin')")
    @GetMapping("/page")
    public IPage<Users> selectPage(Users users,Page page) {
        IPage<Users> iPage=service.selectUsersPage(users,page);
               return iPage;
    }

    /**
     * 修改用户信息
     * @param users
     */
    @PreAuthorize("hasAuthority('Role_admin')")
    @PutMapping("/update")
    public void updateUsers(@Param("user") Users users) {
        service.updateUsers(users);
    }

    /**
     * 删除用户信息
     * @param users
     */
    @PreAuthorize("hasAuthority('Role_admin')")
    @DeleteMapping("/delete")
    public void deleteByUsersId(Users users){
        service.deleteByUsersId(users);
    }

    /**
     * 添加用户
     * @param users
     */
    @PreAuthorize("hasAuthority('Role_admin')")
    @PostMapping("/insert")
    public void addUsers(Users users){
        service.addUsers(users);
    }

    /**
     * 导出excel表格
     * @param users
     * @param page
     * @param response
     */
    @PreAuthorize("hasAuthority('Role_admin')")
    @GetMapping("/exportexcel")
    public void exportExcel(Users users,Page page,HttpServletResponse response)throws IOException {
        //根据条件查询数据库数据list
        IPage<Users> iPage=service.selectUsersPage(users,page);
        //设置表名，引脚名，文件格式，及写入list数据到excel中
        EasyPoiUtil.exportExcel(iPage.getRecords(),"忍者人员信息统计","忍者人员信息",Users.class,"客户人员统计表.xls",response);
    }

    /**
     * excel导入
     * @param file
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('Role_admin')")
    @PostMapping("/importexcel")
    public void importExcel(MultipartFile file) throws Exception {
        //准备一些导入的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        //把上传的excel文件中的数据获取出来
        List<Users> list =ExcelImportUtil.importExcel(file.getInputStream(),Users.class,params);
        log.info("list集合:{}",list);
        //把员工进行保存
        for (Users users:list) {
            if (users!=null){
                service.addUsers(users);
            }
        }
    }
}
