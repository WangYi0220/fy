package com.sl.fy.controller;

import com.sl.fy.pojo.RichText;
import com.sl.fy.pojo.TeacherCard;
import com.sl.fy.service.TeacherCardService;
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

@Api(tags = "导师海报列表")
@RestController
public class TeacherCardController {
    @Autowired
    private TeacherCardService teacherCardService;

    @ApiOperation(value = "获取导师海报列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teaOpenID", value = "导师ID, 不传全查", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "是否展示导师 0否 1是 不传全查", paramType = "query")
    })
    @RequestMapping(value = "/getTeacherCardList", method = RequestMethod.GET)
    public List<TeacherCard> getTeacherCardList(@ApiIgnore @RequestParam Map<String, Object> param) {
        System.out.println(param);
        return teacherCardService.getTeacherCardList(param);
    }

    @ApiOperation(value = "根据导师openID获取导师海报详情")
    @RequestMapping(value = "/getTeacherCard", method = RequestMethod.GET)
    public Map<String, Object> getTeacherCard(String openID) {
        return teacherCardService.getTeacherCard(openID);
    }

    @ApiOperation(value = "根据导师openID获取导师富文本")
    @RequestMapping(value = "/getTeacherCardAndRichText", method = RequestMethod.GET)
    public Map<String, Object> getTeacherCardAndRichText(String teaOpenID) {
        return teacherCardService.getTeacherCardAndRichText(teaOpenID);
    }

    @ApiOperation(value = "更新导师海报(richTextID字段不用传)")
    @RequestMapping(value = "/upTeacherCard", method = RequestMethod.POST)
    public String upTeacherCard(TeacherCard teacherCard, MultipartFile file) {
        teacherCardService.upTeacherCard(teacherCard, file);
        return "ok";
    }

    @ApiOperation("添加导师富文本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teaOpenID", value = "导师编号", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "富文本内容", paramType = "query"),
    })
    @RequestMapping(value = "/saveTeacherCardRichText", method = RequestMethod.POST)
    public String saveTeacherCardRichText(@ApiIgnore String teaOpenID, @ApiIgnore RichText richText) {
        teacherCardService.saveTeacherCardRichText(teaOpenID, richText);
        return "ok";
    }

    @ApiOperation("添加导师海报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teaOpenID", value = "导师编号", paramType = "query"),
            @ApiImplicitParam(name = "pic", value = "导师海报", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "导师是否展示", paramType = "query"),
    })
    @RequestMapping(value = "/saveTeacherCard", method = RequestMethod.POST)
    public String saveTeacherCard(MultipartFile file, @ApiIgnore TeacherCard teacherCard) {
        teacherCardService.saveTeacherCard(teacherCard, file);
        return "ok";
    }
}
