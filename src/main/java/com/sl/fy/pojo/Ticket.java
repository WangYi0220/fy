package com.sl.fy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ticket implements Serializable {
    private String stuOpenID;//学员编号
    private int sum;//可使用的优惠券数量
    private int usingNub;//正在使用中的优惠券数量
    private int usedNub;//已经使用的优惠券
}
