package com.sl.fy.controller;

import com.sl.fy.pojo.Student;
import com.sl.fy.pojo.UserFlag;
import com.sl.fy.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Api(tags = "学员")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "学员填写信息或者修改信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subFlag", value = "是否点击提交按钮", paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "dataFlag", value = "资料是否完整(即用户填写了所有信息)", paramType = "query", dataType = "boolean")
    })
    @RequestMapping(value = "/upStuInfo", method = RequestMethod.POST)
    public String upStuInfo(Student student, @ApiIgnore UserFlag userFlag, MultipartFile file) {
        studentService.upStuInfo(student, userFlag, file);
        return "ok";
    }

    @ApiOperation(value = "根据学员编号获取学员详细信息")
    @RequestMapping(value = "/getStuInfoByOpenID", method = RequestMethod.GET)
    public Student getStuInfoByOpenID(String openID) {
        return studentService.getStuInfoByOpenID(openID);
    }

    @ApiOperation(value = "获取用户验证信息")
    @RequestMapping(value = "/IsDataIntegrity", method = RequestMethod.GET)
    public Map<String, Object> isDataIntegrity(String openID) {
        return studentService.isDataIntegrity(openID);
    }

    @ApiOperation(value = "根据学员编号(详细)或者名字（可模糊）或者获取所有学员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuOpenID", value = "学生编号(不必须)", paramType = "query"),
            @ApiImplicitParam(name = "stuName", value = "学生姓名(不必须)", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页数", paramType = "query")
    })
    @RequestMapping(value = "getStuAllInfo", method = RequestMethod.GET)
    public List<Student> getStuAllInfo(@ApiIgnore @RequestParam Map<String, Object> param) {
        return studentService.getStuAllInfo(param);
    }

    @ApiOperation(value = "获取总记录数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuOpenID", value = "学生编号(不必须)", paramType = "query"),
            @ApiImplicitParam(name = "stuName", value = "学生姓名(不必须)", paramType = "query")
    })
    @RequestMapping(value = "/getDataTotal", method = RequestMethod.GET)
    public int getDataTotal(@ApiIgnore @RequestParam Map<String, Object> param) {
        return studentService.getDataTotal(param);
    }
}
