package com.sl.fy.service;

import com.sl.fy.mapper.ModelMapper;
import com.sl.fy.pojo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * 设置二维码管理员
     * @param aId
     * @param mId
     */
    @Override
    public void setModelAdmin(int aId, int mId) {
        modelMapper.setModelAdmin(aId, mId);
    }

    /**
     * 添加管理模块
     * @param name
     */
    @Override
    public void addModel(String name) {
        modelMapper.addModel(name);
    }

    /**
     * 查询所有模块
     * @return
     */
    @Override
    public List<Model> getModel() {
        return modelMapper.getModel();
    }



}
