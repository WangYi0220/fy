package com.sl.fy.mapper;

import com.sl.fy.pojo.TeacherCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherCardMapper {
    List<TeacherCard> getTeacherCardList(@Param("param") Map<String, Object> param);

    Map<String, Object> getTeacherCard(@Param("teaOpenID") String teaOpenID);

    void upTeacherCard(TeacherCard teacherCard);

    Map<String, Object> getTeacherCardAndRichText(@Param("teaOpenID") String teaOpenID);

    void saveTeacherCardRichText(@Param("teaOpenID") String teaOpenID, @Param("richTextID") int richTextID);

    void saveTeacherCard(TeacherCard teacherCard);
}
