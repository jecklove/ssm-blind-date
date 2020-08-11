package org.jecklove.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jecklove.entity.Photo;
import org.jecklove.entity.Region;
import org.jecklove.entity.ResultBean;
import org.jecklove.entity.User;
import org.jecklove.service.PhotoService;
import org.jecklove.service.RegionService;
import org.jecklove.service.UserService;

import org.jecklove.utils.UploadUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    @Autowired
    private RegionService regionService;


    @RequestMapping("list")
    public String getList(@RequestParam(required = true,defaultValue = "1") Integer pageNum,
                          @RequestParam(required = true,defaultValue = "5") Integer pageSize,
                          Integer pageMax, Model model, User user){

        user.getDataMap().put("realName",user.getRealName());
        user.setRealName(null);
        if (pageMax != null){
            if(pageNum > pageMax){
                pageNum = pageMax;
            }
        }



        List<User> userList = userService.getList(user,pageNum,pageSize);

        PageInfo<User> pageInfo = new PageInfo<>(userList);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("user",user);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("userList",userList);

        return "user/main";
    }

    @RequestMapping("add")
    public String addOne(){

        return "forward:save";
    }

    @RequestMapping("update")
    public String update(Integer userId, Model model){

        User user = userService.getOne(userId);
        if(user.getRegionId() != null){
            long regionId = user.getRegionId();
            String areaStr = String.valueOf(regionId).substring(0,6)+"000000";

            Long areaId = Long.valueOf(areaStr);

            Region area = regionService.getOne(areaId);
            log.info("=====area=========>{}",area);

            String provinceName = area.getFullName().split(",")[0];
            String cityName = area.getFullName().split(",")[1];
            String areaName = area.getFullName().split(",")[2];

            model.addAttribute("provinceName",provinceName);
            model.addAttribute("cityName",cityName);
            model.addAttribute("areaName",areaName);
        }


//        Region region = regionService.getOne();
//        log.info("=========查询到区划==>{}",region);
//        List<Region> regionList = regionService.getList(region);
//        log.info("====通过区划查询到区划所有信息===>{}",regionList.toArray());
        


        List<Photo> photoList = photoService.getList(userId);
        user.setPhotoList(photoList);
        log.info("========user======>{}",user);

        model.addAttribute("user",user);

        return "user/input";
    }

    @RequestMapping("save")
    public String save1(User user, Long county, MultipartFile[] files, HttpServletRequest request) throws IOException {

        //查询
        List<Region> regionList = regionService.getList(Region.builder().id(county).build());

        regionList.forEach(x->user.setRegionId(x.getId()));


        //调用文件上传工具类上传文件,获得文件名
        log.info("======files.length======={}",files.length);


        if (user.getId()==null){
            //插入保存
            userService.insert(user, files, request);
        }else {
            //修改保存
            userService.update(user, files, request);
        }

        return "redirect:list";
    }

    @RequestMapping("search")
    public String search(Model model,User user){



        return "forward:list";
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object delete(@RequestParam(value = "ids") List<Integer> ids){


        int result = userService.delete(ids);
        ResultBean resultBean = ResultBean.builder().code(200).message("success").data(result).build();
        return resultBean;

    }

    @ResponseBody
    @RequestMapping("deleteOne")
    public Object delete(Integer id){
        ResultBean resultBean = new ResultBean();

        if(id != null){
            User user = userService.getOne(id);

            List<Integer> ids = new ArrayList<>();
            ids.add(user.getId());
            int result = userService.delete(ids);
            resultBean = ResultBean.builder().code(200).message("success").data(result).build();

        }

        return resultBean;
    }

    @ResponseBody
    @RequestMapping("check")
    public Object check(){


        ResultBean resultBean = new ResultBean();


        return resultBean;
    }
}
