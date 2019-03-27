package com.sl.fy.service;

import com.sl.fy.pojo.Student;
import com.sl.fy.pojo.UserFlag;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StudentService {
    void upStuInfo(Student student, UserFlag userFlag, MultipartFile file);

    Student getStuInfoByOpenID(String openID);

    Map<String, Object> isDataIntegrity(String openID);

    void afterPayment(String stuOpenID, String pic);

    List<Student> getStuAllInfo(Map<String, Object> param);

    int getDataTotal(@Param("param") Map<String, Object> param);
}
