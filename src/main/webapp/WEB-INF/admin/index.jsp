<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
<title>管理员</title>
<script src="<%=contextPath%>/static/js/jquery-3.1.1.js"></script>
<script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
<script src="<%=contextPath%>/static/js/webuploader.js"></script>
<script src="<%=contextPath%>/static/js/cxuploader2.js"></script>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=contextPath%>/static/css/webuploader.css"
	rel="stylesheet">
<link href="<%=contextPath%>/static/css/cxuploader.css" rel="stylesheet">
<style type="text/css">
.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".a-upload").on(
						"change",
						"input[type='file']",
						function() {
							var filePath = $(this).val();
							if (filePath.indexOf("jpg") != -1
									|| filePath.indexOf("png") != -1) {
								$(".fileerrorTip").html("").hide();
								var arr = filePath.split('\\');
								var fileName = arr[arr.length - 1];
								$(".showFileName").html(fileName);
							} else {
								$(".showFileName").html("");
								$(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！")
										.show();
								return false
							}
						})
				$("#menu_select ul li a").each(function() {
					$(this).click(function() {
						$("#categoryBtn").html($(this).attr("name"));
						attrRel = $(this).attr("rel")
						$("#paramInput").attr("value", attrRel);
						/* var attrValue = $("#aaa").attr("rel") */
					});
				});
			});
	function save() {
		$.post("/uploadPic", function(data) {
			if (data.status == 200) {
				alert("成功")
			}
		});
	}
	function confirmDel() {
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">文章列表
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;  
			&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
			<a class="info" href="/" role="button">首页</a>  &nbsp; &nbsp;
			<a class="info" href="/sang/updatePwd" role="button">修改密码</a>  &nbsp; &nbsp;              
				    <a class="info" href="/sang/logout" role="button">登出</a>
			</div>
			<div class="panel-body">
				<p>
					<a class="btn btn-primary btn-sm" href="/sang/writePage"
						role="button">写博客</a> <a class="btn btn-primary btn-sm"
						role="button" data-toggle="modal" data-target="#uploadPicture">上传图片或视频</a>
					<!-- 		<a class="btn btn-primary btn-sm" role="button" data-toggle="modal"
						data-target="#uploadVideo">上传视频</a> -->
				</p>
			</div>

			<table class="table">
				<tr class="info">
					<td>编号</td>
					<td>标题</td>
					<td>摘要</td>
					<td style="text-align: center" colspan="2">操作</td>
				</tr>
				<c:forEach begin="0" var="article" items="${userArticles}" step="1">
					<tr>
						<td>${article.id}</td>
						<td>${article.title}</td>
						<td>${article.summary}</td>
						<td><a href="/sang/update/${article.id}">修改</a></td>
						<td><a href="/sang/delete/${article.id}"
							onclick="return confirmDel()">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<!-- uploadPicture->Modal -->
	<div class="modal fade" id="uploadPicture" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span style="font-size: 30px;">上传文件</span>
					</h4>
				</div>
				<div class="modal-body">
					<form action="/uploadPic" method="post"
						enctype="multipart/form-data">
						<!-- 分类栏 -->
						<div class="row">
							<div class="col-lg-6">
								<div class="input-group">
									<div class="input-group-btn" id="menu_select">
										<button id="categoryBtn" type="button"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											分类 <span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a rel="1" name="首页">首页</a></li>
											<li><a name="JavaWeb专栏" rel="2">JavaWeb专栏</a></li>
											<li><a name="Android专栏" rel="3">Android专栏</a></li>
											<li><a name="React Native专栏" rel="4">React Native专栏
											</a></li>
											<li><a name="Ubuntu专栏" rel="5">Ubuntu专栏</a></li>
										</ul>
									</div>
									<input type="text" name="title" class="form-control"
										placeholder="标题">
								</div>
							</div>
						</div>
						<!-- 分类栏结束 -->
						<input id="paramInput" name="paramInput" type="hidden" value="1" />
						<!-- 内容 -->
						<div>
							<input type="text" name="content" placeholder="内容" />
						</div>
						<div class="content">
							<ul>
								<li>
									<div class="uploder-container">

										<div class="cxuploder">
											<div class="queueList">
												<span style="font-weight: bolder; color: red">文件大小不可超过50m</span>
												<input id="uploadSomething" type="file" name="file" />
											</div>

											<div>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button id="uploadButton" type="submit"
													class="btn btn-primary">保存</button>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- model end -->
</body>
</html>
