<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/29
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String root = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
</head>
<body>
    <h1 style="color: #9c191a;">${msg}</h1>
    <form method="post" action="<%=root%>/login.do">
        姓名：<input type="text" name="userName" />
        密码：<input type="password" name="password" />
        <input type="submit" value="登录" />
    </form>
</body>
</html>
