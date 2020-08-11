package org.jecklove.dao.impl;

import org.jecklove.dao.RegionDao;
import org.jecklove.entity.Region;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends SqlSessionDaoSupport implements RegionDao {

    private static final String namespace = "mapping.RegionMapper.";


    public RegionDaoImpl(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public Region getOne(Long id) {
        return getSqlSession().selectOne(namespace+"getOne",id);
    }

    @Override
    public List<Region> getList(Region region) {
        return getSqlSession().selectList(namespace+"getList",region);
    }
}
