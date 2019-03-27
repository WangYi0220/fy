package com.sl.fy.controller;

import com.sl.fy.pojo.Model;
import com.sl.fy.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "模块管理")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @ApiOperation("设置模块管理员管理员")
    @RequestMapping(value = "/setQrCodeAdmin", method = RequestMethod.POST)
    public String setQrCodeAdmin(int aId, int mId){
        modelService.setModelAdmin(aId, mId);
        return "ok";
    }

    @ApiOperation("添加模块")
    @RequestMapping(value = "/addModel", method = RequestMethod.POST)
    public String addModel(String name){
        modelService.addModel(name);
        return "ok";
    }

    @ApiOperation("查询所有模块")
    @RequestMapping(value = "/getModel", method = RequestMethod.GET)
    public List<Model> getModel(){
        return modelService.getModel();
    }

    
}
