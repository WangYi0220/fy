package com.sl.fy.service;

import com.sl.fy.pojo.Choose;

import java.util.List;
import java.util.Map;

public interface ChooseService {
    String chooseTeacher(Choose choose);

    void chooseStudent(Choose choose, List<String> stuOpenIDs);

    List<Map<String, Object>> getStudentByTeaOpenID(String teaOpenID, int flag);

    void giveMark(Choose choose);

    Map<String, Object> getMark(int id);

    Map<String, Object> getChooseByStuID(String stuOpenID);
}
