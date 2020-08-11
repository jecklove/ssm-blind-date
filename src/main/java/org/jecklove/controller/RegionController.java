package org.jecklove.controller;


import org.jecklove.entity.Region;
import org.jecklove.entity.ResultBean;
import org.jecklove.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ResponseBody
    @RequestMapping("province")
    public Object province(){

        Region region = Region.builder().level((byte)1).build();
        List<Region> regionList = regionService.getList(region);

        ResultBean resultBean = ResultBean.builder().code(200).message("请求成功！").data(regionList).build();

        return resultBean;
    }

    @ResponseBody
    @RequestMapping("child")
    public Object child(Long parentId){
        Region region = Region.builder().parentId(parentId).build();
        List<Region> regionList = regionService.getList(region);

        ResultBean resultBean = ResultBean.builder().code(200).message("请求成功！").data(regionList).build();

        return resultBean;
    }
}
