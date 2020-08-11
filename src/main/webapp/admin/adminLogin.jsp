
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>adminLogin</title>

    <link rel="stylesheet" href="../static/layui/css/layui.css">

    <style>
        label.error {
            margin-left: 10px;
            font-weight: bold;
            color: #EA5200;
            font-size: 2px;
        }
    </style>
</head>
<body class="layui-layout-body">

    <div class="layui-container" >
        <div style="position:absolute;left: 30%;top: 150px;border: #1E9FFF solid 1px">
            <div class="layui-bg-blue" style="padding: 10px">
                <h2 class="layui-col-lg-offset4">管理员登录</h2>
            </div>
        <br>
        <form id="loginForm" class="layui-form"
              lay-filter="formConfig" name="loginForm">
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" required lay-verify="required" placeholder="请输入账号"
                            value="${cookie.username.value}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                          value="${cookie.password.value}" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <img src="${ctx}/getVerifyCode" id="kaptchaImage" alt="验证码">
                <label class="layui-form-label">验证码: </label>
                <div class="layui-input-inline">
                <input type="text" name="verifyCode" required lay-verify="required" placeholder="请输入验证码"
                        autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="submit" id="login">登录</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
            <div class="layui-form-item">
                <p class="layui-col-md-offset4" style="color:red;" id="message"></p>
            </div>
        </form>
        </div>
    </div>

    <script src="../static/layui/layui.all.js"></script>
    <script type="text/javascript" src="${ ctx }/static/js/jquery-3.5.1.js"></script>
<script type="text/javascript">

$(function () {

    $("#loginForm").submit(function(){
        $.ajax({
            url: '${ctx}/admin/login',
            type: 'GET',
            data:{
                'username': $('[name="username"]').val(),
                'password': $('[name="password"]').val(),
                'verifyCode': $('[name="verifyCode"]').val(),
                'rememberMe': $('[name="rememberMe"]').val()
            },
            dateType: 'json',
            contentType: 'text/json,charset=utf-8',
            success: function (result) {
                // console.log(result);

                // let json = JSON.parse(result);

                // console.log(json.code);

                if (result.code == 200) {

                   location.href ="${ctx}/user/list";
                } else {
                    $('#message').text(result.message);
                    $('#kaptchaImage').attr('src', $('#kaptchaImage').attr('src'));
                }
            },
            error: function() {
                console.log('登录接口请求失败');
            }

        });
        return false;
    });
})


    $('#kaptchaImage').click(function () {
            $(this).attr('src', '${ctx}/getVerifyCode?' + Math.floor(Math.random() * 100));

        })

</script>

</body>
</html>
