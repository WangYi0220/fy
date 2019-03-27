package com.sl.fy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class QRCode implements Serializable {
    private int id;//二维码编号
    private String title;//二维码标题
    private String remark;//二维码备注
    private String qrCode;//二维码
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expires;//二维码到期时间
    private String openID;//所属导师
    private int flag;//0会员群 1导师会员群 2导师面试群
}
