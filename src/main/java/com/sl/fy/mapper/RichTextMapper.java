package com.sl.fy.mapper;

import com.sl.fy.pojo.RichText;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RichTextMapper {
    List<RichText> getRichTextOrList(@Param("id") Integer id, @Param("flag") Integer flag);

    void upRichText(RichText richText);

    void saveRichText(RichText richText);
}
