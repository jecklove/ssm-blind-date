package org.jecklove.service;

import org.jecklove.entity.Region;

import java.util.List;

public interface RegionService {

    Region getOne(Long id);
    List<Region> getList(Region region);

}
