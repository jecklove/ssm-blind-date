package org.jecklove.dao.impl;

import org.jecklove.dao.UserDao;
import org.jecklove.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    private static final String namespace = "mapping.UserMapper.";

    public UserDaoImpl(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    @Override
    public int delete(List<Integer> ids) {
        return getSqlSession().delete(namespace+"delete",ids);
    }

    @Override
    public int insert(User user) {
        return getSqlSession().insert(namespace+"insert",user);
    }

    @Override
    public User getOne(Integer id) {
        return getSqlSession().selectOne(namespace+"getOne",id);
    }

    @Override
    public List<User> getList(User user) {
        return getSqlSession().selectList(namespace+"getList",user);
    }

    @Override
    public int update(User user) {
        return getSqlSession().update(namespace+"update",user);
    }

}
