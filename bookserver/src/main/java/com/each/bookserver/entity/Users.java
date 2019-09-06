package com.each.bookserver.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Each
 * @since 2019-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "users",resultMap = "BaseResultMap")
@ExcelTarget("users")
public class Users implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @Excel(name = "忍者姓名",orderNum = "1")
    private String name;

    /**
     * 年龄
     */
    @Excel(name = "年龄",orderNum ="2")
    private Integer age;

    /**
     * 性别
     */
    @Excel(name = "性别",orderNum = "3",replace={"男_1","女_2"},suffix = "生")
    private Integer sex;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话",orderNum = "4")
    private String telephone;

    /**
     * 住址
     */
    @Excel(name = "住址",orderNum = "5")
    private String address;

    /**
     * 籍贯
     */
    @Excel(name = "籍贯",orderNum = "6")
    private String nativeplace;

    /**
     * 加入时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone="GMT+8")
    @Excel(name = "加入时间", databaseFormat = "yyyyMMdd", format = "yyyy-MM-dd",orderNum = "6")
    private Date jointime;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", nativeplace='" + nativeplace + '\'' +
                ", jointime=" + jointime +
                '}';
    }
}
