<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
	<h3>新規ユーザー登録</h3>
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
<form action="signup" method="post"><br />

	<label for="loginId">ログインID</label>
	<input name="loginId" id="loginId"/><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="name">ユーザー名</label>
	<input name="name" id="name"/><br />

	<label for="branch">所属支店</label>
	<select name="branch">
		<c:forEach items="${ branchList }" var="branch">
			<option value="${ branch.id }">${ branch.name }</option>
		</c:forEach>
	</select><br />

	<label for="position">所属部署・役職</label>
	<select name="position">
		<c:forEach items="${ positionList }" var="position">
			<option value="${ position.id }">${ position.name }</option>
		</c:forEach>
	</select><br />

	<input type="submit" value="登録" /> <br />
<br />
	<a href="./">戻る</a>
</form>
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>