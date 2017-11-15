<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>请登录</title>
    <script src="<%=contextPath%>/static/js/jquery-3.1.1.js"></script>
    <script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/css/signin.css" rel="stylesheet"/>
    
    
</head>
<body>
<div class="container">

    <form id="loginForm" class="form-signin" action="/sang/dologin" method="post">
        <h2 class="form-signin-heading">请登录</h2>
        <div>
           <%if(request.getAttribute("error")!=null){%>
            <%=request.getAttribute("error")%>
            <%}%>
        </div>
        <label for="inputEmail" class="sr-only">请输入用户名</label>
        <input type="text" id="inputEmail" name="username" class="form-control" placeholder="用户名" pattern="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$" required autofocus invalid >
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword"  name="password" class="form-control" placeholder="密码" pattern="^[a-zA-Z0-9]{6,12}$" required invalid>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <label><a href="<%=contextPath%>/views/register">免费注册</a></label>
            <label><a href="http://localhost:8081/">返回主页</a></label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

</div>

</body>
</html>
