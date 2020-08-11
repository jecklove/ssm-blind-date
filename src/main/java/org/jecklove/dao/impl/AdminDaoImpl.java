package org.jecklove.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.jecklove.dao.AdminDao;
import org.jecklove.entity.Admin;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Primary
@Repository
public class AdminDaoImpl extends SqlSessionDaoSupport implements AdminDao {

    private static final String namespace = "mapping.AdminMapper.";

    public AdminDaoImpl(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(Admin admin) {
        return getSqlSession().insert(namespace+"insert",admin);
    }

    @Override
    public Admin getOne(Admin admin) {
        return getSqlSession().selectOne(namespace+"getOne",admin);
    }

    @Override
    public List<Admin> getList(Admin admin) {
        return getSqlSession().selectList(namespace+"getList",admin);
    }

    @Override
    public int update(Admin admin) {
        return getSqlSession().update(namespace+"update",admin);
    }

    @Override
    public int delete(Integer id) {
        return getSqlSession().delete(namespace+"delete",id);
    }
}
