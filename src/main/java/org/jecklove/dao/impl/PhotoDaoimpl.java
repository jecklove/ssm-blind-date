package org.jecklove.dao.impl;

import org.apache.ibatis.annotations.Param;
import org.jecklove.dao.PhotoDao;
import org.jecklove.entity.Photo;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDaoimpl extends SqlSessionDaoSupport implements PhotoDao {

    public static final String namespace = "mapping.PhotoMapper.";

    public PhotoDaoimpl(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int delete(List<Integer> ids) {
        return getSqlSession().delete(namespace+"delete",ids);
    }

    @Override
    public int insert(List<Photo> photoList) {
        return getSqlSession().insert(namespace+"insert",photoList);
    }

    @Override
    public List<Photo> getList(Integer userId) {
        return getSqlSession().selectList(namespace+"getList",userId);
    }

    @Override
    public int update(Photo photo) {
        return getSqlSession().update(namespace+"update",photo);
    }
}
