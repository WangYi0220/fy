package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherCard implements Serializable {
    private String teaOpenID;//导师编号
    private String pic;//导师海报
    private int richTextID;//导师富文本编号
    private boolean flag;//是否展示
}
