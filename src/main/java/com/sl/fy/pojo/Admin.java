package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    private int id;//管理员编号
    private String openID;//管理员openID
    private String username;//登录名
    private String password;//登陆密码
    private String realname;//管理员真实姓名
    private int flag;//是否激活
}
