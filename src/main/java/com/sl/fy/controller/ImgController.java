package com.sl.fy.controller;

import com.sl.fy.common.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "图片")
@RestController
public class ImgController {
    private static final String PATH_PREFIX = "text/";

    @ApiOperation(value = "图片上传(上传成功返回图片所在位置相对路径，否则未null)")
    @RequestMapping(value = "/ImgUp", method = RequestMethod.POST)
    public String ImgUp(MultipartFile[] files){
        for (MultipartFile file: files) {
            if (file != null && !file.isEmpty()){
                return   FileUtil.fileUP(file,PATH_PREFIX);
            }
        }
        return null;
    }
}
