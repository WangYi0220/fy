package com.sl.fy.service;

import com.sl.fy.common.utils.FileUtil;
import com.sl.fy.mapper.RichTextMapper;
import com.sl.fy.mapper.TeacherCardMapper;
import com.sl.fy.pojo.RichText;
import com.sl.fy.pojo.TeacherCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TeacherCardServiceImpl implements TeacherCardService {
    private static final String PATH_PREFIX = "teacherCard/";

    @Autowired
    private TeacherCardMapper teacherCardMapper;

    @Autowired
    private RichTextMapper richTextMapper;

    /**
     * 获取导师海报列表
     * 无参数全查
     *
     * @param param flag(0不展示的导师 1展示的导师), teaOpenID
     * @return
     */
    @Override
    public List<TeacherCard> getTeacherCardList(Map<String, Object> param) {
        return teacherCardMapper.getTeacherCardList(param);
    }

    /**
     * 根据导师openID获取导师海报详情
     *
     * @param teaOpenID
     * @return
     */
    @Override
    public Map<String, Object> getTeacherCard(String teaOpenID) {
        return teacherCardMapper.getTeacherCard(teaOpenID);
    }

    /**
     * 更新导师海报
     *
     * @param teacherCard
     */
    @Override
    public void upTeacherCard(TeacherCard teacherCard, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String s = FileUtil.fileUP(file, PATH_PREFIX);
            teacherCard.setPic(s);
        }
        teacherCardMapper.upTeacherCard(teacherCard);
    }

    /**
     * 获取导师的富文本
     *
     * @param teaOpenID
     * @return
     */
    @Override
    public Map<String, Object> getTeacherCardAndRichText(String teaOpenID) {
        return teacherCardMapper.getTeacherCardAndRichText(teaOpenID);
    }

    /**
     * 添加导师富文本
     *
     * @param teaOpenID
     * @param richText
     */
    @Override
    public void saveTeacherCardRichText(String teaOpenID, RichText richText) {
        richText.setFlag(2);
        richTextMapper.saveRichText(richText);
        teacherCardMapper.saveTeacherCardRichText(teaOpenID, richText.getId());
    }

    /**
     * 添加导师海报
     * @param teacherCard
     * @param file
     */
    @Override
    public void saveTeacherCard(TeacherCard teacherCard, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String s = FileUtil.fileUP(file, PATH_PREFIX);
            teacherCard.setPic(s);
        }
        teacherCardMapper.saveTeacherCard(teacherCard);
    }
}
