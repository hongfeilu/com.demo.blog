<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
<title>修改博客</title>
<script src="<%=contextPath%>/static/js/jquery-3.1.1.js"></script>
<script src="<%=contextPath%>/static/editormd/editormd.js"></script>
<script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=contextPath%>/static/editormd/css/editormd.css" />
<link rel="shortcut icon"
	href="https://pandao.github.io/editor.md/favicon.ico"
	type="image/x-icon" />
</head>
<body>
	<form method="post" action="/sang/updateWrite">
		<div class="container">
			<br> <br>
			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
						<div class="input-group-btn">
							<c:choose>
								<c:when test="${article==null}">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" id="categoryBtn">分类</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" id="categoryBtn">${catrgoryNameByArticle}
									</button>
								</c:otherwise>
							</c:choose>

							<ul class="dropdown-menu">
								<c:forEach var="category" items="${categories}">
									<li><a
										onclick="selectCategory('${category.id}','${category.displayName}');">${category.displayName}</a>
									</li>
								</c:forEach>
							</ul>
						</div>
						<input name="categoryid" id="cateoryInput" type="hidden">
						<c:choose>
							<c:when test="${article==null}">
								<input type="text" class="form-control" placeholder="标题"
									name="title">
							</c:when>
							<c:otherwise>
								<input name="id" type="hidden" value="${article.id}">
								<input type="text" class="form-control" placeholder="标题"
									name="title" value="${article.title}">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<br>
		<%-- <div>
			<img src="${article.file}">
		</div>
		<br> --%>
		<div id="layout" style="width: 100%; height: 100%">
			<div id="test-editormd">
				<c:choose>
					<c:when test="${article==null}">
						<textarea style="display: none;" name="content"></textarea>
					</c:when>
					<c:otherwise>
						<textarea style="display: none;" name="content">${article.content}</textarea>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

		<script type="text/javascript">
			var testEditor;
			$(function() {
				testEditor = editormd("test-editormd",
						{
							width : "100%",
							height : 640,
							syncScrolling : "single",
							path : "/static/editormd/lib/",
							imageUpload : true,
							imageFormats : [ "jpg", "jpeg", "gif", "png",
									"bmp", "webp" ],
							imageUploadURL : "./php/upload.php?test=dfdf"
						});
			});
		</script>
		<div class="row">
			<div class="col-md-6 col-md-offset-6">
				<p>
					<input type="submit" class="btn btn-primary btn-lg" role="button"
						value="修改">
				</p>
			</div>
		</div>
	</form>
	<script>
		function selectCategory(id, displayName) {
			$("#categoryBtn").html(displayName);
			$("#cateoryInput").val(id);
		}
	</script>
</body>
</html>
