package org.example.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.springboot.entity.Admin;
import org.example.springboot.exception.CustomException;
import org.example.springboot.mapper.AdminMapper;
import org.example.springboot.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public void add(Admin admin) {
        String username = admin.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin != null) {
            throw new CustomException("500", "用户已存在");
        }
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword("admin");
        }
        if (StrUtil.isBlank(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole("ADMIN");
        adminMapper.insert(admin);
    }

    public List<Admin> selectAll(Admin admin) {
        List<Admin> list = adminMapper.selectAll(admin);
        return list;
    }


    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }


    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);

    }


    public void update(Admin admin) {
        adminMapper.updateById(admin);

    }

    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public Admin login(Admin admin) {
        String username = admin.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin == null) {
            throw new CustomException("500", "用户名不存在");
        }

        String password = admin.getPassword();
        if (!dbAdmin.getPassword().equals(password))
            throw new CustomException("500", "账号或密码错误");
        return dbAdmin;
    }
}
