package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Guide implements Serializable {
    private int id;//编号
    private String title;//标题
    private String content;//内容
    private String img;//头部图片
    private boolean flag;//0学员页 1导师页
}
