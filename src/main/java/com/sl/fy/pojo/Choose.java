package com.sl.fy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Choose implements Serializable {
    private int id;
    private String stuOpenID;//学员编号
    private String teaOpenID;//导师编号
    private String reason;//选择理由
    private int flag;//0等待老师选择 1面试 2被选 3未选
    private int star;//导师对学员的打分
    private String remark;//导师对学生的评语
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date overTime;//私塾到期时间
    private boolean overFlag;//私塾是否到期
}
