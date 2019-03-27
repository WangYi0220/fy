package com.sl.fy.service;

import com.sl.fy.mapper.RichTextMapper;
import com.sl.fy.pojo.RichText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RichTextServiceImpl implements RichTextService {
    @Autowired
    private RichTextMapper richTextMapper;

    /**
     * 查询所有富文本或者查询指定的富文本
     * @param id
     * @return
     */
    @Override
    public List<RichText> getRichTextOrList(Integer id, Integer flag) {
        return richTextMapper.getRichTextOrList(id, flag);
    }

    /**
     * 更新富文本
     * @param richText
     */
    @Override
    public void upRichText(RichText richText) {
        richTextMapper.upRichText(richText);
    }

    /**
     * 保存富文本
     * @param richText
     * @return
     */
    @Override
    public void saveRichText(RichText richText) {
        richTextMapper.saveRichText(richText);
    }
}
