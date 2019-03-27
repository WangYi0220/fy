package com.sl.fy.mapper;

import com.sl.fy.pojo.Student;
import com.sl.fy.pojo.UserFlag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    void saveStuInfo(@Param("stuOpenID") String stuOpenID, @Param("pic") String pic);

    void upStuInfo(Student student);

    Student getStuInfoByOpenID(String openID);

    void upUserFlag(UserFlag userFlag);

    Map<String, Object> isDataIntegrity(@Param("openID") String openID);

    List<Student> getStuAllInfo(@Param("param") Map<String, Object> param);

    int getDataTotal(@Param("param") Map<String, Object> param);
}
