package com.sl.fy.controller;

import com.sl.fy.pojo.Choose;
import com.sl.fy.service.ChooseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Api(tags = "学员导师双向选择")
@RestController
public class ChooseController {
    @Autowired
    private ChooseService chooseService;

    @ApiOperation(value = "学员选择导师(操作成功ok,赋羽券不足no)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuOpenID", value = "学生编号", paramType = "query"),
            @ApiImplicitParam(name = "teaOpenID", value = "导师编号", paramType = "query"),
            @ApiImplicitParam(name = "reason", value = "选择导师理由", paramType = "query")
    })
    @RequestMapping(value = "/chooseTeacher", method = RequestMethod.POST)
    public String chooseTeacher(@ApiIgnore Choose choose) {
        String s = chooseService.chooseTeacher(choose);
        return s;
    }

    @ApiOperation(value = "导师选择学生")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "导师编号", name = "teaOpenID", paramType = "query"),
            @ApiImplicitParam(value = "0等待老师选择 1面试 2被选 3未选", name = "flag", paramType = "query"),
            @ApiImplicitParam(name = "stuOpenIDs", allowMultiple = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/chooseStudent", method = RequestMethod.POST)
    public String chooseStudent(@ApiIgnore Choose choose, @ApiIgnore @RequestParam(value = "stuOpenIDs") List<String> stuOpenIDs) {
        System.out.println(choose);
        System.out.println(stuOpenIDs);
        chooseService.chooseStudent(choose, stuOpenIDs);
        return "ok";
    }

    @ApiOperation("根据导师编号查询选择该导师的入选/待选的学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teaOpenID", value = "导师编号", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "0等待老师选择 1面试 2被选 3未选", paramType = "query")

    })
    @RequestMapping(value = "/getStudentByTeaOpenID", method = RequestMethod.GET)
    public List<Map<String, Object>> getStudentByTeaOpenID(String teaOpenID, int flag) {
        return chooseService.getStudentByTeaOpenID(teaOpenID, flag);
    }

    @ApiOperation(value = "导师打分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query"),
            @ApiImplicitParam(name = "stuOpenID", value = "学生编号", paramType = "query"),
            @ApiImplicitParam(name = "star", value = "评分(整)", paramType = "query"),
            @ApiImplicitParam(name = "remark", value = "评语", paramType = "query")
    })
    @RequestMapping(value = "/giveMark", method = RequestMethod.POST)
    public String giveMark(@ApiIgnore Choose choose) {
        chooseService.giveMark(choose);
        return "ok";
    }

    @ApiOperation(value = "获取导师评语")
    @RequestMapping(value = "/getMark", method = RequestMethod.GET)
    public Map<String, Object> getMark(int id) {
        return chooseService.getMark(id);
    }

    @ApiOperation("获取学员选择情况（导师编号，是否入选）")
    @RequestMapping(value = "getChooseByStuID", method = RequestMethod.GET)
    public Map<String, Object> getChooseByStuID(String stuOpenID) {
        return chooseService.getChooseByStuID(stuOpenID);
    }
}
