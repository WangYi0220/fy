package com.sl.fy.service;

import com.sl.fy.mapper.AdminMapper;
import com.sl.fy.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登陆(返回0为用户不存在 1为密码错误 成功直接返回用户所有信息)
     * @param admin
     * @return
     */
    @Override
    public Object login(Admin admin) {
        Admin login = adminMapper.login(admin.getUsername());
        if (login == null) {
            return 0;//当用户不存在时
        }
        if (!admin.getPassword().equals(login.getPassword())) {
            return 1;//当密码错误时
        }
        return login;//无异常正常返回
    }

    /**
     * 添加管理员 当用户名重复时返回false
     * @param admin
     * @return
     */
    @Override
    public String addAdmin(Admin admin) {
        Admin login = adminMapper.login(admin.getUsername());
        if (login != null) {
            return "false";
        }
        adminMapper.addAdmin(admin);
        return "ok";
    }

    /**
     * 激活管理员账户
     * @param id
     * @param openID
     */
    @Override
    public void activeAdmin(int id, String openID) {
        adminMapper.activeAdmin(id, openID);
    }

    /**
     * 获取所有管理员
     * @return
     */
    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.getAllAdmin();
    }
}
