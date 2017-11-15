<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=contextPath%>/static/js/search.js"></script>
<script src="<%=contextPath%>/static/js/jquery-3.1.1.js"></script>
<script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
<div class="row">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=contextPath%>/">sang的专栏</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="nv1">
                    <li class="active" id="firstPage"><a href="<%=contextPath%>/">首页</a></li>
                    <li id="webPage"><a href="<%=contextPath%>/column/webPage">JavaWeb专栏</a></li>
                    <li id="androidPage"><a href="<%=contextPath%>/column/androidPage">Android专栏</a>
                    </li>
                    <li id="rnPage"><a href="<%=contextPath%>/column/rnPage">React Native专栏</a>
                    </li>
                    <li id="ubuntuPage"><a href="<%=contextPath%>/column/ubuntuPage">Ubuntu专栏</a>
                    </li>
                    
                </ul>
                <div class="form">
                <div class="navbar-form navbar-right">
                    <div class="input-group">
	                    <input type="text" class="form-control" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');" placeholder="搜索">
	                    <span class="input-group-btn">
	       					<button class="btn btn-default"  onclick="search('key');return false;"  type="button">搜索</button>
	      				</span> 
                    </div>
                </div>
               </div>
                <c:if test="${user !=null }">
                <div class="navbar-form navbar-right">
                   <div style="color:white" >${user.username} </div>
                   <a href="<%=contextPath%>/views/logout">登出</a>
                   <a href="<%=contextPath%>/sang">个人中心</a>
                </div>
                </c:if>
                <c:if test="${user ==null }">
                <div class="navbar-form navbar-right">
                   <a href="<%=contextPath%>/views/login">登陆</a>
                   <a href="<%=contextPath%>/views/register">注册</a>
                </div>
                </c:if>
            </div>
        </div>
    </nav>
</div>
<script>
    var href = location.href;
    var id = href.substring(href.lastIndexOf("/") + 1, href.length);
    if (id=="") {
        id = "firstPage";
    }
    var ids = ["firstPage", "webPage", "androidPage", "rnPage", "ubuntuPage"];
    for (var i = 0; i < ids.length; i++) {
        if (id == ids[i]) {
            $("#" + id).attr("class", "active");
        } else {
            $("#" + ids[i]).removeAttr("class");
        }
    }
</script>