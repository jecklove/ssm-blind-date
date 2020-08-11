package org.jecklove.service.impl;

import org.jecklove.dao.PhotoDao;
import org.jecklove.entity.Photo;
import org.jecklove.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    @Transactional
    public int delete(List<Integer> ids) {
        return photoDao.delete(ids);
    }

    @Override
    @Transactional
    public int insert(List<Photo> photoList) {

        return photoDao.insert(photoList);
    }

    @Override
    public List<Photo> getList(Integer userId) {
        return photoDao.getList(userId);
    }

    @Override
    @Transactional
    public int update(Photo photo) {
        return photoDao.update(photo);
    }
}
