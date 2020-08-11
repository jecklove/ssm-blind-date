package org.jecklove.service.impl;

import org.jecklove.dao.RegionDao;
import org.jecklove.entity.Region;
import org.jecklove.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public Region getOne(Long id) {
        return regionDao.getOne(id);
    }

    /**
     * 获取省份
     *
     * @return
     */
    @Override
    public List<Region> getList(Region region) {
        return regionDao.getList(region);
    }

}
