<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>添加/修改页面</title>
    <link rel="stylesheet" href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <style>
        .form-box {
            margin: 50px 250px 0px 250px;
            padding: 0 10px 20px 0;
            border: 1px solid dodgerblue;
            width: 800px;
        }

        .title {
            width: 800px;
            background-color: dodgerblue;
            margin-top: -20px;
            margin-left: -1px;
            margin-bottom: 10px;
            height: 50px;
            text-align: center;

        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-box">
        <div class="title">
            <h3>添加/修改页面</h3>
        </div>
        <form action="/user/save" class="form-horizontal" enctype="multipart/form-data" method="post">
            <div class="form-group" style="display: none">
                <label for="id" class="col-md-2 control-label">id：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="text" name="id" id="id" class="form-control" value="${user.id}" >
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-md-2 control-label">用户名：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="text" name="username" id="username" class="form-control" value="${user.username}">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-2 control-label">密码：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="text" name="password" id="password" class="form-control" value="${user.password}">
                </div>
            </div>
            <div class="form-group">
                <label for="realName" class="col-md-2 control-label">真实姓名：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="text" name="realName" id="realName" class="form-control" value="${user.realName}">
                </div>
            </div>
            <div class="form-group">
                <label for="age" class="col-md-2 control-label">年龄：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="text" name="age" id="age" class="form-control" value="${user.age}">
                </div>
            </div>
            <div class="form-group">
                <label for="birthday" class="col-md-2 control-label">生日：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="date" name="birthday" id="birthday" class="form-control" value="<fmt:formatDate value="${user.birthday }" type="date" pattern="yyyy-MM-dd"/>">
                </div>
            </div>
            <div class="form-group">
                <label  class="col-md-2 control-label">性别：</label>
                <div class="col-md-offset-2 col-md-10">
                   <input type="radio" name="sex" value="男" title="男" checked>男
                    <input type="radio" name="sex" value="女" title="女" >女
                <%--                    <input type="text" name="sex" id="sex" class="form-control" value="${user.sex}">--%>
                </div>
            </div>
            <div class="form-group">
                <label for="tel" class="col-md-2 control-label">电话：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="text" name="tel" id="tel" class="form-control" value="${user.tel}">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-md-2 control-label">邮箱：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="email" name="email" id="email" class="form-control" value="${user.email}">
                </div>
            </div>
            <div class="form-group" >
                <label for="province" class="col-md-2 control-label">住址：</label>
                <div class="col-md-offset-2 col-md-10" >

                    <select name="province" id="province" class="layui-form-select">
                        <c:if test="${provinceName==null}">
                            <option value="0">--请选择--</option>
                        </c:if>
                        <c:if test="${provinceName != null}">
                            <option value="0">${provinceName}</option>
                        </c:if>
                    </select>
                    <select name="city" id="city" class="layui-form-select">
                        <c:if test="${cityName == null}">
                            <option value="0">--请选择--</option>
                        </c:if>
                        <c:if test="${cityName != null}">
                            <option value="0">${cityName}</option>
                        </c:if>
                    </select>
                    <select name="county" id="county" class="layui-form-select">
                        <c:if test="${areaName == null}">
                            <option value="0">--请选择--</option>
                        </c:if>
                       <c:if test="${areaName != null}">
                           <option value="0">${areaName}</option>
                       </c:if>

                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="remark" class="col-md-2 control-label">备注：</label>
                <div class="col-md-offset-2 col-md-10">
                    <input type="text" name="remark" id="remark" class="form-control" value="${user.remark}">
                </div>
            </div>
            <div class="form-group">
                <label for="photo" class="col-md-2 control-label">照片：</label>
                <div id="photo">
                    <c:forEach items="${user.photoList}" var="photo" varStatus="vs">
                        <img src="/upload/${photo.goal}" alt="照片" width="100px" height="130px">
                    </c:forEach>
                    <div class="col-md-10 col-md-offset-2" id="photoBox">
                    <input type="file" name="files" class="col-md-offset-1" value="选择一张图片">
                    </div>
                    <button type="button" onclick="add()">添加照片</button>

                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <button type="submit">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ ctx }/static/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.min.js"></script>
<script>
$(function () {
   /* $.validate.addMethod('usernameEnable',function (value,element,param) {
        let result =false;
        $.ajax({
            url:'/user/check',
            type: 'get',
            data: {
                'username': value
            },
            async: true,
            dataType: 'json',

            success: function (r) {
                result = r.data;
            },
            error: function () {
                console.log("检查用户名失败")
            }
        });
        return result;
    })*/

})

    let addPhoto = "<div>" +
        "<input type=\"file\" name=\"files\" class=\"col-md-4\">" +
        " <button id='delBtn' type='button' onclick='del(this)' class='col-md-offset-4'>删除</button></div>";
   function add(){
       $("#photoBox").append(addPhoto);
   };

   function del(obj){
       $(obj).parent("div").remove();
   }
    $(function() {
        // ajax获取所有省份
        $.ajax({
            url: '${ctx}/region/province',
            type: 'get',
            dataType: 'json',
            success: function(result) {
                console.log(result);
                let provinceList = result.data;
                for (let province of provinceList) {
                    $('#province').append($('<option value="'+province.id+'">'+province.name+'</option>'));
                }
            },
            error: function() {
                console.log("获取所有省份接口请求失败！");
            }
        });

        // 当省份改变时，获取市级数据
        $('#province').change(function() {

            $('#city').html('<option value="0">--请选择--</option>');
            $('#county').html('<option value="0">--请选择--</option>');

            let parentId = $('#province').val();
            $.ajax({
                url: '${ctx}/region/child',
                type: 'get',
                data: {
                    'parentId': parentId
                },
                dataType: 'json',
                success: function(result) {
                    console.log(result);
                    let cityList = result.data;
                    for (let city of cityList) {
                        $('#city').append($('<option value="'+city.id+'">'+city.name+'</option>'));
                    }
                },
                error: function() {
                    console.log("获取所有城市接口请求失败！");
                }
            });


        });

        // 城市改变获取对应的区县数据
        $('#city').change(function() {

            $('#county').html('<option value="0">--请选择--</option>');

            let parentId = $('#city').val();
            $.ajax({
                url: '${ctx}/region/child',
                type: 'get',
                data: {
                    'parentId': parentId
                },
                dataType: 'json',
                success: function(result) {
                    console.log(result);
                    let countyList = result.data;
                    for (let county of countyList) {
                        $('#county').append($('<option value="'+county.id+'">'+county.name+'</option>'));
                    }
                },
                error: function() {
                    console.log("获取所有区县接口请求失败！");
                }
            });


        });
    })
</script>
</body>
</html>
