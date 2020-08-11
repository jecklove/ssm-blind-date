package org.jecklove.dao;

import org.jecklove.entity.User;

import java.util.List;

public interface UserDao {
    int delete(List<Integer> ids);

    int insert(User user);

    User getOne(Integer id);

    List<User> getList(User user);

    int update(User user);

}