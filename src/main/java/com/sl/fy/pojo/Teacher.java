package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Teacher implements Serializable {
    private String teaOpenID;//导师编号(微信)
    private String teaName;//导师真实姓名
    private String phone;//电话号码
    private String waChat;//微信号
    private String teaCity;//用户所在城市
    private String teaOrg;//所属学校全称或所在机构/公司
    private String identity;//身份
    private String introduce;//个人简介
    private String profession;//所属行业
    private String claim;//对学员要求
    private String value;//私塾价值
    private String other;//其他补充说明
    private String pic;//文件
}
