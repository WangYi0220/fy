package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Model implements Serializable {
    private int id;//模块编号
    private String name;//模块名称
}
