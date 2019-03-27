package com.sl.fy.controller;

import com.sl.fy.pojo.Guide;
import com.sl.fy.service.GuideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "用户信息登记页面头部说明")
@RestController
public class GuideController {
    @Autowired
    private GuideService guideService;

    @ApiOperation(value = "获取信息登记页面(传flag指定页面，不传就所有)")
    @ApiImplicitParam(name = "flag", value = "0学员页 1导师页")
    @RequestMapping(value = "/getGuideList", method = RequestMethod.GET)
    public List<Guide> getGuideList(@ApiIgnore Integer flag) {
        return guideService.getGuideList(flag);
    }

    @ApiOperation(value = "更新信息等级表页面内容")
    @RequestMapping(value = "/upGuide", method = RequestMethod.POST)
    public String upGuide(MultipartFile file, Guide guide) {
        guideService.upGuide(guide, file);
        return "ok";
    }
}
