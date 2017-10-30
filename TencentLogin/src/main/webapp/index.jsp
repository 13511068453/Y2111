<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="qq/do_login.chm"><img alt="" src="img/Connect_logo_5.png"></a>
<%
String code=request.getParameter("code");
String state=request.getParameter("state");
if(code!=null&&state!=null){
	request.getRequestDispatcher("qq/login").forward(request, response);
}
%>
</body>
</html>