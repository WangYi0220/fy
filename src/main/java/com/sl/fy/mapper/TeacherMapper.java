package com.sl.fy.mapper;

import com.sl.fy.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    void saveTeaInfo(Teacher teacher);

    void upTeaInfo(Teacher teacher);

    List<Teacher> getTeacherList(@Param("teaName") String teaName, @Param("teaOpenID") String teaOpenID);
}
