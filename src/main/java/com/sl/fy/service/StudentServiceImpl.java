package com.sl.fy.service;

import com.sl.fy.common.utils.FileUtil;
import com.sl.fy.mapper.StudentMapper;
import com.sl.fy.pojo.Student;
import com.sl.fy.pojo.UserFlag;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    private static final String PATH_PREFIX = "student/";

    private static UserFlag userFlag;

    /**
     * 学员填写个人信息或修改个人信息
     *
     * @param student
     * @param userFlag
     */
    @Override
    public void upStuInfo(Student student, UserFlag userFlag, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String s = FileUtil.fileUP(file, PATH_PREFIX);
            student.setStuFile(s);
        }
        studentMapper.upStuInfo(student);
        if (userFlag.getSubFlag() != null || userFlag.getDataFlag() != null) {//当userFlag不为空时修改tb_user_flag
            userFlag.setOpenID(student.getStuOpenID());
            studentMapper.upUserFlag(userFlag);
        }
    }

    /**
     * 根据用户编号获取用户详细信息
     *
     * @param openID
     * @return
     */
    @Override
    public Student getStuInfoByOpenID(String openID) {
        return studentMapper.getStuInfoByOpenID(openID);
    }

    /**
     * 获取用户验证信息
     *
     * @param openID
     * @return
     */
    @Override
    public Map<String, Object> isDataIntegrity(String openID) {
        return studentMapper.isDataIntegrity(openID);
    }


    /**
     * 付款之后调用该方法
     * 创建会员及添加赋羽券
     *
     * @param stuOpenID
     */
    @Override
    public void afterPayment(String stuOpenID, String pic) {
        studentMapper.saveStuInfo(stuOpenID, pic);
    }

    /**
     * 根据学员编号(详细)或者名字（可模糊）或者获取所有学员信息
     *
     * @param param
     * @return
     */
    @Override
    public List<Student> getStuAllInfo(Map<String, Object> param) {
        int currentPage = Integer.parseInt((String) param.get("currentPage"));//获取当前页
        System.out.println(currentPage);
        param.put("start", (currentPage - 1) * 10);//设置数据起始位置
        return studentMapper.getStuAllInfo(param);
    }

    /**
     * 多条件获取总记录数
     *
     * @param param
     * @return
     */
    @Override
    public int getDataTotal(Map<String, Object> param) {
        return studentMapper.getDataTotal(param);
    }
}
