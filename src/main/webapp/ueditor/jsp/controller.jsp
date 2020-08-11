<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
	// 解决乱码
	request.setCharacterEncoding("utf-8");
	response.setHeader("Content-Type", "text/html");

	// 获取项目根路径的磁盘真实路径
	String rootPath = application.getRealPath("/");

	// 执行核心操作
	String result = new ActionEnter(request, rootPath).exec();

	// 处理列出指定目录下的文件地址错误
	// 磁盘地址转为相对路径
	String action = request.getParameter("action");
	if (action != null && (action.equals("listimage") || action.equals("listfile"))) {
		rootPath = rootPath.replace("\\", "/");
		result = result.replace(rootPath, "");
	}

	// 响应json数据
	out.write(result);
%>