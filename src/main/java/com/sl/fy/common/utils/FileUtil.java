package com.sl.fy.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static final String PATH = "/root/fy/img/";

    public static String fileUP(MultipartFile file, String pathPrefix) {
        try {
            String fileName = file.getOriginalFilename();
            int i = fileName.lastIndexOf(".");
            System.out.println("zzzzzz:"+i);
            String suffix = fileName.substring(i);
            String path = PATH + pathPrefix;
            String newFileName = UUIDUtils.getUUID() + suffix;
            File targetFile = new File(path, newFileName);
            file.transferTo(targetFile);
            return pathPrefix + newFileName;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void fileDel(String targetPath) {
        File file = new File(PATH + targetPath);
        System.out.println(PATH + targetPath);
        file.delete();
    }
}
