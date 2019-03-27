package com.sl.fy.service;

import com.sl.fy.pojo.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherService {
    void saveTeaInfo(Teacher teacher, MultipartFile[] file);

    List<Teacher> getTeacherList(String teaName, String teaOpenID);
}
