package com.sl.fy.service;

import com.sl.fy.pojo.RichText;

import java.util.List;

public interface RichTextService {
    List<RichText> getRichTextOrList(Integer id, Integer flag);

    void upRichText(RichText richText);

    void saveRichText(RichText richText);
}
