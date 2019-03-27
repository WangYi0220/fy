package com.sl.fy.service;

import com.sl.fy.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    Object login(Admin admin);

    String addAdmin(Admin admin);

    void activeAdmin(@Param("id") int id, @Param("openID") String openID);

    List<Admin> getAllAdmin();
}
