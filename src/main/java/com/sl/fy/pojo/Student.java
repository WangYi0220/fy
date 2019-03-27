package com.sl.fy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Student implements Serializable {
    private String stuOpenID;//学员编号(微信)
    private String pic;//用户头像
    private String stuName;//学员真实姓名
    private String phone;//电话号码
    private String waChat;//微信号
    private String stuCity;//用户所在城市
    private String stuOrg;//所属学校全称或所在机构/公司
    private String stuDept;//学校或所在部门
    private boolean stuSex;//学员性别 0男 1女
    private String identity;//学员身份
    private String stuTag;//正向标签
    private String stuTag2;//负面标签
    private String stuKill;//个人技能
    private String stuExperience;//工作/学习经历
    private String giveAuto;//10年后的自己
    private String expectTeacher;//理想中的人生导师
    private String giveTeacher;//对你未来的人生导师说一句话
    private String harvestList;//收获集合
    private String know;//了解赋羽
    private String stuFile;//文件
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;//生日
}
