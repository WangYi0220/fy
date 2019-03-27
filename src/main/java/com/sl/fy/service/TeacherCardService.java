package com.sl.fy.service;

import com.sl.fy.pojo.RichText;
import com.sl.fy.pojo.TeacherCard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TeacherCardService {
    List<TeacherCard> getTeacherCardList(Map<String, Object> param);

    Map<String, Object> getTeacherCard(String teaOpenID);

    void upTeacherCard(TeacherCard teacherCard, MultipartFile file);

    Map<String, Object> getTeacherCardAndRichText(String teaOpenID);

    void saveTeacherCardRichText(String teaOpenID, RichText richText);

    void saveTeacherCard(TeacherCard teacherCard, MultipartFile file);
}
