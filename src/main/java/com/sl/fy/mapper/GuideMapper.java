package com.sl.fy.mapper;

import com.sl.fy.pojo.Guide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuideMapper {
    List<Guide> getGuideList(@Param("flag") Integer flag);

    void upGuide(Guide guide);
}
