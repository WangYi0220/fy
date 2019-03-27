package com.sl.fy.controller;

import com.sl.fy.pojo.RichText;
import com.sl.fy.service.RichTextService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "富文本")
@RestController
public class RichTextController {
    @Autowired
    private RichTextService richTextService;

    @ApiOperation(value = "获取指定(id)富文本或者获取所有富文本(可分类)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "富文本编号", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "富文本分类 0学员页面 1导师页面 2导师", paramType = "query")
    })
    @RequestMapping(value = "/getRichText", method = RequestMethod.GET)
    public List<RichText> getRichTextOrList(Integer id, Integer flag) {
        return richTextService.getRichTextOrList(id, flag);
    }

    @ApiOperation(value = "更新富文本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "富文本编号", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "富文本内容", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "富文本分类 0学员页面 1导师页面 2导师", paramType = "query")
    })
    @RequestMapping(value = "/upRichText", method = RequestMethod.POST)
    public String upRichText(@ApiIgnore RichText richText){
        richTextService.upRichText(richText);
        return "ok";
    }

    @ApiOperation(value = "保存富文本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "富文本内容", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "富文本分类 0学员页面 1导师页面 2导师", paramType = "query")
    })
    @RequestMapping(value = "/saveRichText", method = RequestMethod.POST)
    public String saveRichText(@ApiIgnore RichText richText){
        richTextService.saveRichText(richText);
        return "ok";
    }
}
