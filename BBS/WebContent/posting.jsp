<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
	<h3>新規投稿画面</h3>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="posting" method="post">
	<label for="title">件名</label><br />
	<input name="title" id="title"/> <br />

	<label for="text">本文</label><br />
	<textarea name="text" cols="50" rows="5" id="text"></textarea> <br />

	<label for="category">カテゴリ</label><br />
	<input name="category" id="category"/> <br />

	<input type="submit" value="投稿" /> <br />
</form>
<br />
<div class="header">
	<a href="home">戻る</a><br />
</div>
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>