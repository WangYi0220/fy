package com.sl.fy.mapper;

import com.sl.fy.pojo.Choose;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChooseMapper {
    void chooseTeacher(Choose choose);

    void chooseStudent(@Param("choose") Choose choose, @Param("stuOpenIDs") List<String> stuOpenIDs);

    List<Map<String, Object>> getStudentByTeaOpenID(@Param("teaOpenID") String teaOpenID, @Param("flag") int flag);

    void giveMark(Choose choose);

    Map<String, Object> getMark(int id);

    List<Map<String, Object>> checkTeacherGiveMark();

    Map<String, Object> getChooseByStuID(@Param("stuOpenID") String stuOpenID);

    List<Integer> getOverTime();

    void setOverFlag(@Param("ids") List<Integer> ids);
}
