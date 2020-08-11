package org.jecklove.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class UploadUtil {

    public static final String upload(HttpServletRequest request, MultipartFile file) throws IOException {

        String filePath = request.getSession().getServletContext().getRealPath("/upload");
        log.info("=============={}",filePath);
        //获取原始图片的扩展名
        String originaFilename = file.getOriginalFilename();

        //新的文件名
        String newFileName = UUID.randomUUID()+originaFilename;

        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName);

        log.info("====>{}",targetFile);
        //把本地文件上传到封装文件位置的全路径
        file.transferTo(targetFile);

        log.info("=======>{}",file.toString());

        return newFileName;
    }
}
