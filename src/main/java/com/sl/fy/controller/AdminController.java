package com.sl.fy.controller;

import com.sl.fy.pojo.Admin;
import com.sl.fy.service.AdminService;
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

@RestController
@Api(tags = "管理员")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "管理员登陆(返回0为用户不存在 1为密码错误 成功直接返回用户所有信息)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", paramType = "query")
    })
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@ApiIgnore Admin admin) {
        return adminService.login(admin);
    }

    @ApiOperation(value = "添加管理员 当用户名重复时返回false")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", paramType = "query"),
            @ApiImplicitParam(name = "realname", value = "管理员真实姓名", paramType = "query")
    })
    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public String addAdmin(@ApiIgnore Admin admin) {
        return adminService.addAdmin(admin);
    }

    @ApiOperation("激活管理员账户（添加微信openID）")
    @RequestMapping(value = "activeAdmin", method = RequestMethod.POST)
    public String activeAdmin(int id, String openID) {
        adminService.activeAdmin(id, openID);
        return "ok";
    }

    @ApiOperation("获取所有管理员")
    @RequestMapping(value = "getAllAdmin", method = RequestMethod.GET)
    public List<Admin> getAllAdmin() {
        return adminService.getAllAdmin();
    }
}
