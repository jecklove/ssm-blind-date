package org.jecklove.dao;

import org.jecklove.entity.Region;

import java.util.List;

public interface RegionDao {

    Region getOne(Long id);
    List<Region> getList(Region region);

}