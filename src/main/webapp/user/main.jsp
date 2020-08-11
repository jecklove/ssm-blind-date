<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!--fmt标签引入，格式化日期-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>

    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>用户详情页</title>
    <link rel="stylesheet" href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/layui/layui.all.js">

</head>
<body>
<div class="container" style="text-align: center">

    <form action="/user/search" method="post">
        <!-- <input type="hidden" name="method" value="list" /> -->
        <table class="table ">
            <tr>
                <th>性别</th>
                <td><input type="text" name="sex" value="${user.sex}" size="30" /></td>

                <th>年龄</th>
                <td><input type="text" name="age" value="${user.dataMap.startAge}"  size="10" />至
                    <input type="text" name="age" value="${user.dataMap.endAge}"  size="10" /></td>
                <th>真实姓名</th>
                <td><input type="text" name="real_name" value="${user.dataMap.realName}"></td>
            </tr>
            <tr>
                <th></th>
                <th></th>
                <th><button class="btn btn-info"><a href="/user/input.jsp">添加</a></button></th>
                <th colspan="2"><input type="submit" value="查询" class="btn btn-info"/></th>
            </tr>
        </table>
    </form>
    <table class="table table-bordered">
        <input type="button" onclick="batchDelete()" class="button btn-info col-md-1" value="批量删除"/>
        <thead>
        <tr>
            <th><input type="checkbox" class="checkbox" id="select-all" onclick="selectAll();"></th>
            <th>序号</th>
            <th>id</th>
            <th>真实姓名</th>
            <th>年龄</th>
            <th>生日</th>
            <th>性别</th>
            <th>电话</th>
            <th>邮箱</th>
            <th>住址</th>
            <th>操作</th>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user" varStatus="vs">
            <tr>
                <td><input type="checkbox"  value="${user.id}" class="checkbox" name="selected" onclick="oneToAll();"></td>
                <td>${vs.count}</td>
                <td>${user.id}</td>
                <td>${user.realName}</td>
                <td>${user.age}</td>
                <td><fmt:formatDate value="${user.birthday }" type="date" pattern="yyyy-MM-dd"/></td>
                <td>${user.sex}</td>
                <td>${user.tel}</td>
                <td>${user.email}</td>
                <td style="max-width: 180px">${user.dataMap.get("address")}</td>
                <td><button><a href="/user/update?userId=${user.id}">修改</a></button> <button><a href="#" onclick="deleteUser(${user.id})">删除</a></button> <button><a href="/user/detail?id=${user.id}">详情</a></button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <form id="pageForm" action="${ctx}/user/list" method="post">
            <div class="layui-table-page">
                <div id="layui-table-page1">
                    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-2">
                        <c:if test="${pageInfo.isFirstPage eq false}">
                            <span><a href="${ctx}/user/list?pageNum=${pageInfo.navigateFirstPage}&pageSize=${ pageSize }">首页</a></span>
                        </c:if>
                        <c:if test="${pageInfo.isFirstPage eq true}">
                            <span style="padding:0 12px; color: darkgrey">首页</span>
                        </c:if>
                        <c:if test="${pageInfo.hasPreviousPage eq true}">
                            <span><a href="${ctx}/user/list?pageNum=${pageInfo.prePage}&pageSize=${ pageSize }">上页</a></span>
                        </c:if>
                        <c:if test="${pageInfo.hasPreviousPage eq false}">
                            <span style="padding:0 12px; color: darkgrey">上页</span>
                        </c:if>
                        <c:if test="${pageInfo.hasNextPage eq true}">
                            <span><a href="${ctx}/user/list?pageNum=${pageInfo.nextPage}&pageSize=${ pageSize }">下页</a></span>
                        </c:if>
                        <c:if test="${pageInfo.hasNextPage eq false}">
                            <span style="padding:0 12px; color: darkgrey">下页</span>
                        </c:if>
                        <c:if test="${pageInfo.isLastPage eq false}">
                            <span><a href="${ctx}/user/list?pageNum=${pageInfo.navigateLastPage}&pageSize=${ pageSize }">尾页</a></span>
                        </c:if>
                        <c:if test="${pageInfo.isLastPage eq true}">
                            <span style="padding:0 12px; color: darkgrey">尾页</span>
                        </c:if>
                        <span style="padding: 0 5px">共 ${ pageInfo.pages } 页 </span>
                        <span class="layui-laypage-skip">到第
                            <label>
                                <input type="text" name="pageNum" min="1" value="${pageInfo.pageNum}" class="layui-input">
                                <input type="hidden" name="pageMax" value="${pageInfo.pages}">
                                <input type="hidden" name="pageSize" value="${pageSize}">
                            </label>页
                            <button type="submit" class="layui-laypage-btn">确定</button>
                        </span>
                        <span class="layui-laypage-count">共 ${pageInfo.total} 条数据</span>
                        <span class="layui-laypage-limits">
                            <select class="select_pageSize" lay-ignore="">
                                <option value="5" ${pageSize eq 5 ? "selected" : ""}>5</option>
                                <option value="10" ${pageSize eq 10 ? "selected" : ""}>10</option>
                                <option value="20" ${pageSize eq 20 ? "selected" : ""}>20</option>
                            </select>
                            条 / 每页
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="../static/js/jquery-3.5.1.js"></script>
<script type="text/javascript">

    function deleteUser(id) {
        alert("确定删除？")
        $.ajax({
            type: "get",
            url: "${ctx}/user/deleteOne",
            data: {id: id},
            dataType: "JSON",
            success: function (result) {
                if (result.code==200){
                    alert("删除成功！")
                    location.reload();
                }else {
                    alert("删除失败！")
                }
            },
            error: function () {
                console.log('删除接口请求失败');
            }
        })

    };
    function batchDelete(){
        //判断至少选择了一项
        let checkedNum = $("input[name='selected']:checked").length;


        if (checkedNum == 0) {
            alert("至少选择一项删除！");
            return;
        }
        if (confirm("确定删除选中的用户？")) {
            let ids = new Array();
            $("input[name='selected']:checked").each(function(){
                if($("#select-all").val()!= null){
                    ids.push($(this).val());
                }

            });
            $.ajax({
                type : "post",
                url : "${ctx}/user/delete",
                data : {"ids" : ids.toString()},
                dataType:"JSON",
                success : function(){
                    alert("删除成功！");
                    location.reload();
                },
                error : function(){
                    alert("刪除失败！")
                }
            });
        }
    };
        function selectAll() {

            if ($("#select-all").is(":checked")) {

                $("[name='selected']").prop("checked", true);
            } else {

                $("[name='selected']").prop("checked", false);
            }
    };
        //获取所有选中checkbox的值

        function queryCheckedValue() {

            var str = "";

            $("input:checkbox[name='selected']:checked").each(function(i) {

                var val = $(this).val();
                str = str + val + "-";

            });

            return str;

        }

    $(".select_pageSize").change(function () {
        let pageSize = $(this).val();
        console.log(pageSize);
        // 把分页参数追加到表单
        $('#pageForm').attr('action', $('#pageForm').attr('action') + "?pageSize=" + pageSize)
        // 提交表单
        $('#pageForm').submit();
    })



</script>
</body>
</html>
