package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RichText implements Serializable {
    private int id;//富文本编号
    private String content;//富文本内容
    private int flag;//富文本分类 0学员页面 1导师页面 2导师
}
