<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
<script src="<%=contextPath%>/static/kindeditor/kindeditor.js"></script>
<script src="<%=contextPath%>/static/kindeditor/lang/zh_CN.js"></script>
<title>详情</title>
</head>
<body>
	<jsp:include page="comm/top.jsp" />
	<div class="container">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title"
					style="font-size: 30px; font-weight: bolder;">${articles.title}</h3>
			</div>
			<div class="panel-body">
				<span style="font-size: 20px; font-weight: bolder;">${articles.content}</span>
			</div>
			<div class="panel-body">
				<img src="${articles.file}">
			</div>
			<div class="panel-body">
				<span
					style="position: relative; left: 900px; font-size: 15px; font-weight: bolder;">${articles.date}</span>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">评论</h3>
			</div>
			<div class="panel-body">
				<span> <c:forEach var="comments" items="${comments}"
						begin="0" step="1">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">${comments.commIp}</h3>
							</div>
							<div class="panel-body">${comments.comment}</div>
						</div>
					</c:forEach>
				</span>
			</div>
		</div>
		<form method="post" action="/sang/comment">
			<textarea id="editor_id" name="comment"
				style="width: 700px; height: 300px;"></textarea>
			<input name="commIp" type="hidden" value="${user.username}"> <input
				name="articleId" type="hidden" value="${articles.id}"> <input
				type="submit" class="btn btn-primary btn-lg" role="button"
				value="发表">
		</form>
	</div>
	<script>
		var commentEditor;
		KindEditor.ready(function(K) {
			commentEditor = K.create("#editor_id");
		});
	</script>
</body>
</html>