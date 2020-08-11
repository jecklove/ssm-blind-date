package org.jecklove.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jecklove.entity.Admin;
import org.jecklove.entity.ResultBean;
import org.jecklove.service.AdminService;
import org.jecklove.utils.Md5Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public Object login(Model model, String username, String password, String verifyCode, String rememberMe, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");

        log.info("==============进入登录验证方法login===============");

        Admin admin = Admin.builder().username(username).password(password).build();

        log.info("=====admin====>{}", admin);
        log.info("=======用户输入的验证码=======>{}", verifyCode);
        ResultBean resultBean = null;

        // 获取正确的验证码
        String realVerifyCode = (String) request.getSession().getAttribute("verifyCode");
        log.info("====正确的验证码===========>{}", realVerifyCode);

        //如果验证码不正确
        if (!verifyCode.equalsIgnoreCase(realVerifyCode)) {
            resultBean = ResultBean.builder().code(400).message("验证码错误！").build();
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultBean;
        }
        /*// 对密码进行加密
        String encodePassword = "";
        try {
            encodePassword = Md5Util.encode(admin.getPassword());
        } catch (Exception e1) {
            e1.printStackTrace();
        }*/

        // 调用登录业务方法
        Admin result = adminService.getOne(admin);

        // 判断是否登录成功
        if (result == null) {
            resultBean = ResultBean.builder().code(400).message("用户名或密码错误！").build();

            return resultBean;
        } else {
            // 保存session
            request.getSession().setAttribute("onlineUser", result);

            Admin onlineUser = (Admin) request.getSession().getAttribute("onlineUser");

            resultBean = ResultBean.builder().code(200).message("登陆成功！").build();
            return resultBean;
        }

    }
}
