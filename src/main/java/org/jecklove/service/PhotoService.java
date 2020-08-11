package org.jecklove.service;

import org.jecklove.entity.Photo;

import java.util.List;

public interface PhotoService {
    int delete(List<Integer> ids);

    int insert(List<Photo> photoList);

    List<Photo> getList(Integer userId);

    int update(Photo photo);

}
