package com.sl.fy.mapper;

import com.sl.fy.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    Admin login(@Param("username") String username);

    void addAdmin(Admin admin);

    void activeAdmin(@Param("id") int id, @Param("openID") String openID);

    List<Admin> getAllAdmin();
}
