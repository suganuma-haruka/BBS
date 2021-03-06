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
	<h2>≪新規投稿画面≫</h2>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<font color="red"><li>エラー：<c:out value="${message}" /></font>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<div class="header">
	<h3>・メニュー</h3>
		<a href="home">ホーム画面</a><br />
</div><br />
<HR width="60%" align="left">

<h3>・新規投稿</h3>
<form action="posting" method="post">
	<label for="title">件名 (50文字以下)</label><br />
	<input name="title" value="${ posting.title }" id="title"/> <br />

	<label for="text">本文 (1,000文字以下)</label><br />
	<textarea name="text" cols="50" rows="5" id="text"><c:out value="${ posting.text }" /></textarea> <br />

	<label for="category">カテゴリー (10文字以下)</label><br />
	<input name="category" value="${ posting.category }" id="category"/><br />
	<br />
	<input type="submit" value="投稿" /> <br />
</form>
<br />
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>