package com.each.bookserver.dto;


import com.each.bookserver.entity.Menu;

import java.util.List;

@lombok.Data
public class Data {
    private String name;
    private Integer user_id;
    private List<String> access;
    private String token;
    private String avatar;
    private List<Menu> routers;
}
