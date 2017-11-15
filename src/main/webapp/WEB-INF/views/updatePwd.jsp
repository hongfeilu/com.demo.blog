<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>修改密码</title>
    <script src="<%=contextPath%>/static/js/jquery-3.1.1.js"></script>
    <script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/css/signin.css" rel="stylesheet"/>
    
    
</head>
<body>
<div class="container">

    <form id="updatePwdForm" class="form-signin" action="/sang/doupdatePwd" method="post">
        <h2 class="form-signin-heading">密码修改界面</h2>
        <div>
           <%if(request.getAttribute("error")!=null){%>
            <%=request.getAttribute("error")%>
            <%}%>
        </div>
        <input type="hidden" name="id" value="${user.id }"/>
        <input type="hidden" name="username" value="${user.username }"/>
         <input type="hidden" name="password" value="${user.password }"/>
        <label for="inputPassword" >原密码</label>
        <input type="password" id="inputoldPassword"  name="oldPassword" class="form-control"  placeholder="请输入原密码" pattern="^[a-zA-Z0-9]{6,12}$" required invalid>
        <label for="inputPassword" >新密码</label>
        <input type="password" id="inputnewPassword"  name="newpassword" class="form-control"  placeholder="请输入新密码" pattern="^[a-zA-Z0-9]{6,12}$" required invalid>
        <label for="inputPassword" >确认密码</label>
        <input type="password" id="inputexitPassword"  name="exitPassword" class="form-control"  placeholder="重新输入新密码" pattern="^[a-zA-Z0-9]{6,12}$" required invalid>
        <button class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
    </form>

</div>
<!--<script type="text/javascript">
    function validateForm() {
    	var oldPwd = document.getElementById('inputoldPassword').value;
    	var newPwd = document.getElementById('inputPassword').value;
    	var exitPwd = document.getElementById('inputexitPassword').value;
    	var pwd = "${user.password}" ;
    	alert(Pwd);
    }
</script>  -->

</body>
</html>
