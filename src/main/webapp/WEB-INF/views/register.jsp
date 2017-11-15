<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>请注册</title>
    <script src="<%=contextPath%>/static/js/jquery-3.1.1.js"></script>
    <script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/css/signin.css" rel="stylesheet"/>
    
    
</head>
<body>
<div class="container">

    <form id="loginForm" class="form-signin"  action="/views/doregister" method="post">
        <h2 class="form-signin-heading">注册界面</h2>
        <div>
           <%if(request.getAttribute("error")!=null){%>
            <%=request.getAttribute("error")%>
            <%}%>
        </div>
        <label for="inputEmail" >请输入用户名</label>
        <input type="text" id="inputEmail" name="username" class="form-control" placeholder="输入有效的邮箱格式" pattern="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$" required autofocus invalid >
        <label for="inputPassword">密码</label>
        <input type="password" id="inputPassword"  name="password" class="form-control" placeholder="输入由数字，字母组成的6-12字符的格式" pattern="^[a-zA-Z0-9]{6,12}$" required invalid>
        <label><a href="/views/login">已注册</a></label>
        <label><a href="http://localhost:8081/">返回主页</a></label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
    </form>

</div>

</body>
</html>
