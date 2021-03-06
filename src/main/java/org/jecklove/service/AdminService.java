package org.jecklove.service;

import org.jecklove.entity.Admin;

import java.util.List;

public interface AdminService {

    int insert(Admin admin);

    Admin getOne(Admin admin);

    List<Admin> getList(Admin admin);

    int update(Admin admin);

    int delete(Integer id);
}
