package com.sl.fy.service;

import com.sl.fy.pojo.Model;

import java.util.List;

public interface ModelService {
    void setModelAdmin(int aId, int mId);

    void addModel(String name);

    List<Model> getModel();
}
