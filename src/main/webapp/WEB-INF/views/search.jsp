<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>${keyword} - 搜索</title>
    <link href="<%=contextPath%>/static/css/mycss.css" rel="stylesheet"/>
</head>
<body>
<%--<%@include file="comm/top.jsp" %>--%>
<jsp:include page="comm/top.jsp"/>

<c:forEach var="article" items="${articles}" begin="0" step="1">
    <div class="row">
        <div class="container">
            <div class="jumbotron">
                <h3>${article.title}</h3>
                <span class="summary">${article.summary}</span><br><br>
                <p><a class="btn btn-primary btn-lg" href="/detail/${article.id}/firstPage"
                      role="button">阅读全文</a></p>
            </div>
        </div>
    </div>
</c:forEach>
<!-- 分页功能 start -->  
    <div align="center">  
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第  
            ${page.pageNow} 页</font> <a href="search?q=${keyword}&pageNow=1">首页</a>  
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">  
                <a href="search?q=${keyword}&pageNow=${page.pageNow - 1}">上一页</a>  
            </c:when>  
            <c:when test="${page.pageNow - 1 <= 0}">  
                <a href="search?q=${keyword}&pageNow=1">上一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="search?q=${keyword}&pageNow=${page.pageNow}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">  
                <a href="search?q=${keyword}&pageNow=${page.pageNow + 1}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">  
                <a href="search?q=${keyword}&pageNow=${page.totalPageCount}">下一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="search?q=${keyword}&pageNow=${page.pageNow}">尾页</a>  
            </c:when>  
            <c:otherwise>  
                <a href="search?q=${keyword}&pageNow=${page.totalPageCount}">尾页</a>  
            </c:otherwise>  
        </c:choose>  
    </div>  
    <!-- 分页功能 End -->  
<script type="text/javascript">

</script>
</body>
</html>
