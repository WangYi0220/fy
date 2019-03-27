package com.sl.fy.mapper;

import com.sl.fy.pojo.Model;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelMapper {
    void setModelAdmin(@Param("adminID") int adminID,@Param("modelID") int modelID);

    void addModel(@Param("name") String name);

    String getModelAdminOpenID(@Param("modelID") int modelID);

    List<Model> getModel();
}
