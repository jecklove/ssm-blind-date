package org.jecklove.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.jecklove.dao.UserDao;
import org.jecklove.entity.Photo;
import org.jecklove.entity.User;
import org.jecklove.service.PhotoService;
import org.jecklove.service.RegionService;
import org.jecklove.service.UserService;
import org.jecklove.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegionService regionService;

    @Autowired
    private PhotoService photoService;

    @Override
    @Transactional
    public int delete(List<Integer> ids) {

        String path = "E:\\ideaproject\\ssm-blind-date\\src\\main\\webapp\\upload\\";
        if(!ids.isEmpty()){
            if (ids.size()!=0){

                for (Integer userId : ids) {
                    List<Photo> photoList = photoService.getList(userId);
                    List<Integer> photoIds = new ArrayList<>();
                    for (Photo p : photoList) {
                        photoIds.add(p.getId());
                    }
                    photoIds.forEach(x->log.info("=========id=====>{}",x));
                    if(photoIds.size()!=0){


                        if(photoList.size() != 0){
                            //旧图片1
                            for (Photo p : photoList) {
                                File oldFile = new File(path+p.getGoal());

                                log.info("=======老照片=======>{}",oldFile);
                                oldFile.delete();//直接删除
                            }

                        }
                        photoService.delete(photoIds);
                    }
                }
            }
        }

        return userDao.delete(ids);
    }

    @Override
    @Transactional
    public int insert(User user, MultipartFile[] files , HttpServletRequest request) throws IOException {
        String newPath = "";
        String path = "E:\\ideaproject\\ssm-blind-date\\src\\main\\webapp\\upload\\";
        int userResult = userDao.insert(user);
        log.info("======插入之后的user=======>{}",user);
        if (files.length != 0){
            for (MultipartFile file : files) {
                if (!file.isEmpty() && user != null){
                    log.info("=======file====={}",file);
                    newPath = UploadUtil.upload(request, file);
                    user.getPhotoList().add(Photo.builder().goal(newPath).userId(user.getId()).build());
                    user.getPhotoList().forEach(x->log.info("============>{}",x));
                }
            }
            user.setPhotoList(user.getPhotoList());
            log.info("==================photoList===============>{}",user.getPhotoList());
            int result = photoService.insert(user.getPhotoList());

            if (result == 0){
                //如果图片插入失败，删除本地已生成的文件

                if(user.getPhotoList().size() != 0) {

                    for (Photo p : user.getPhotoList()) {
                        File oldFile = new File(path + newPath);
                        oldFile.delete();//直接删除
                    }
                }
            }

        }

        return userResult;
    }

    @Override
    public User getOne(Integer id) {
        return userDao.getOne(id);
    }
    @Transactional
    @Override
    public List<User> getList(User user,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<User> userList = userDao.getList(user);
        for (User u : userList) {
            if(u.getRegionId() != null) {
                u.getDataMap().put("address", regionService.getOne(u.getRegionId()).getFullName());
            }else {
                u.getDataMap().put("address", "不详");
            }
        }


        return userList;
    }

    @Override
    @Transactional
    public int update(User user, MultipartFile[] files , HttpServletRequest request) throws IOException {
        String newPath = "";
        String path = "E:\\ideaproject\\ssm-blind-date\\src\\main\\webapp\\upload\\";
        if (files.length != 0){
            for (MultipartFile file : files) {
                if (!file.isEmpty()){
                    log.info("=======file====={}",file);
                    newPath = UploadUtil.upload(request, file);
                    user.getPhotoList().add(Photo.builder().goal(newPath).userId(user.getId()).build());
                    user.getPhotoList().forEach(x->log.info("============>{}",x));
                }
            }

            log.info("==================修改的photoList===============>{}",user.getPhotoList());
            int result = photoService.insert(user.getPhotoList());
            if (result == 0){
                //如果图片插入失败，删除本地已生成的文件

                if(user.getPhotoList().size() != 0) {

                    for (Photo p : user.getPhotoList()) {
                        File oldFile = new File(path + newPath);
                        oldFile.delete();//直接删除
                    }
                }
            }

        }

        return userDao.update(user);
    }

}
