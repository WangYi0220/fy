package com.sl.fy.service;

import com.sl.fy.pojo.Guide;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GuideService {
    List<Guide> getGuideList(Integer flag);

    void upGuide(Guide guide, MultipartFile file);
}
