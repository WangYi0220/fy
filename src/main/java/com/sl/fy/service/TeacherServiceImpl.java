package com.sl.fy.service;

import com.sl.fy.common.utils.FileUtil;
import com.sl.fy.mapper.TeacherMapper;
import com.sl.fy.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    private static final String PATH_PREFIX = "teacher/";

    /**
     * 填写或修改导师信息
     *
     * @param teacher
     * @param file
     */
    @Override
    public void saveTeaInfo(Teacher teacher, MultipartFile[] file) {
        System.out.println(file.length);
        StringBuffer pic = new StringBuffer();
        if (file != null && file.length > 0) {
            for (int i = 0; i < file.length; i++) {
                if (file[i] != null && !file[i].isEmpty()) {
                    System.out.println(file[i].getOriginalFilename());
                    String s = FileUtil.fileUP(file[i], PATH_PREFIX);
                    pic.append(s + ",");
                }
            }
            System.out.println(pic.toString());
            teacher.setPic(pic.toString());
        }
        try {
            teacherMapper.saveTeaInfo(teacher);
        } catch (Exception e) {
            teacherMapper.upTeaInfo(teacher);
        }
    }

    /**
     * 查询所有导师或根据导师名字查(可模糊查)或根据导师openID查询详情
     *
     * @param teaName
     * @return
     */
    @Override
    public List<Teacher> getTeacherList(String teaName, String teaOpenID) {
        return teacherMapper.getTeacherList(teaName, teaOpenID);
    }
}
