package com.sl.fy.service;

import com.sl.fy.common.utils.FileUtil;
import com.sl.fy.mapper.GuideMapper;
import com.sl.fy.pojo.Guide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class GuideServiceImpl implements GuideService {
    private static final String PATH_PREFIX = "guide/";

    @Autowired
    private GuideMapper guideMapper;

    /**
     * 获取信息登记页面(传flag指定页面，不传就所有)
     * @param flag
     * @return
     */
    @Override
    public List<Guide> getGuideList(Integer flag) {
        return guideMapper.getGuideList(flag);
    }

    /**
     * 更新
     * @param guide
     * @param file
     */
    @Override
    public void upGuide(Guide guide, MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = FileUtil.fileUP(file, PATH_PREFIX);
            FileUtil.fileDel(guide.getImg());//删除原来的图片
            guide.setImg(fileName);
        }
        guideMapper.upGuide(guide);
    }
}
