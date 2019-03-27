package com.sl.fy.controller;

import com.sl.fy.pojo.QRCode;
import com.sl.fy.service.QRCodeService;
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

@Api(tags = "二维码")
@RestController
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @ApiOperation(value = "获取二维码集合(无参数时)/会员群/指定导师(学员群/面试群)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "二维码id", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "0会员群 1导师会员群 2导师面试群", paramType = "query"),
            @ApiImplicitParam(name = "teaOpenID", value = "导师ID", paramType = "query")
    })
    @RequestMapping(value = "/getQRCodeList", method = RequestMethod.GET)
    public List<QRCode> getQRCodeList(@RequestParam @ApiIgnore Map<String, String> param) {
        return qrCodeService.getQRCodeList(param);
    }

    @ApiOperation(value = "管理二维码")
    @RequestMapping(value = "/upQRCode", method = RequestMethod.POST)
    public String upQRCode(QRCode qrCode, MultipartFile file) {
        qrCodeService.upQRCode(qrCode, file);
        return "ok";
    }

    @ApiOperation(value = "添加二维码")
    @RequestMapping(value = "/saveQRCode", method = RequestMethod.POST)
    public String saveQRCode(MultipartFile file, QRCode qrCode) {
        qrCodeService.saveQRCode(qrCode, file);
        return "ok";
    }
}
