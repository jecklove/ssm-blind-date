package org.jecklove.service.impl;

import org.jecklove.dao.AdminDao;
import org.jecklove.entity.Admin;
import org.jecklove.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public int insert(Admin admin) {
        return adminDao.insert(admin);
    }

    @Override
    public Admin getOne(Admin admin) {
        return adminDao.getOne(admin);
    }

    @Override
    public List<Admin> getList(Admin admin) {
        return adminDao.getList(admin);
    }

    @Override
    public int update(Admin admin) {
        return adminDao.update(admin);
    }

    @Override
    public int delete(Integer id) {
        return adminDao.delete(id);
    }
}
