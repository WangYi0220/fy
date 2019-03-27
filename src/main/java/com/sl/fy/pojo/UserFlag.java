package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserFlag implements Serializable {
    private String openID;
    private Integer vipFlag;//是否成为会员
    private Integer subFlag;//资料是否提交
    private Integer dataFlag;//资料是否完整
}
