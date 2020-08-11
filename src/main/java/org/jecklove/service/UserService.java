package org.jecklove.service;

import org.jecklove.entity.Photo;
import org.jecklove.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface UserService {
    int delete(List<Integer> ids);

    int insert(User user, MultipartFile[] files, HttpServletRequest request) throws IOException;

    User getOne(Integer id);

    List<User> getList(User user,Integer pageNum,Integer pageSize);

    int update(User user, MultipartFile[] files,HttpServletRequest request) throws IOException;

}
