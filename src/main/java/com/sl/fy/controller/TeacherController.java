package com.sl.fy.controller;

import com.sl.fy.pojo.Teacher;
import com.sl.fy.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "导师")
@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "填写或修改导师信息")
    @RequestMapping(value = "/saveTeaInfo", method = RequestMethod.POST)
    public String saveTeaInfo(Teacher teacher, MultipartFile[] file){
        teacherService.saveTeaInfo(teacher, file);
        return "ok";
    }

    @ApiOperation(value = "查询所有导师或根据导师名字查(可模糊查)或根据导师openID查询详情")
    @RequestMapping(value = "/getTeacherList", method = RequestMethod.GET)
    public List<Teacher> getTeacherList(String teaName, String teaOpenID){
        return teacherService.getTeacherList(teaName, teaOpenID);
    }
}
