<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー新規登録</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
	<h2>≪ユーザー新規登録≫</h2>

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
		<a href="home">ホーム画面</a>
		<a href="userControl">ユーザー管理画面</a>
</div><br />
<HR width="60%" align="left">

<h3>・ユーザー新規登録</h3>
<form action="signup" method="post">

	<label for="userId">ログインID</label><br />
	<input name="userId" value="${user.userId}" id="userId"/><br />

	<label for="password">パスワード</label><br />
	<input name="password" type="password" id="password"/> <br />

	<label for="passwordCheck">パスワード(確認)</label><br />
	<input name="passwordCheck" type="password" id="password"/> <br />

	<label for="name">ユーザー名</label><br />
	<input name="name" value="${user.name}" id="name"/><br />

	<label for="branch">所属支店</label><br />
	<select name="branch">
		<option value="0">--選択してください--</option>
			<c:forEach items="${ branchList }" var="branch">
				<option value="${ branch.id }" <c:if test="${ branch.id == user.branchId }">selected</c:if> >
					<c:out value="${ branch.name }" />
				</option>
			</c:forEach>

<%--		<c:forEach items="${ branchList }" var="branch">
			<option value="${ branch.id }">
				<c:out value="${ branch.name }" />
			</option>
		</c:forEach>
--%>
	</select><br />

	<label for="position">所属部署・役職</label><br />
	<select name="position">
	<option value="0">--選択してください--</option>
		<c:forEach items="${ positionList }" var="position">
			<option value="${ position.id }" <c:if test="${ position.id == user.positionId }">selected</c:if> >
				<c:out value="${ position.name }" />
			</option>
		</c:forEach>

<%--		<c:forEach items="${ positionList }" var="position">
			<option value="${ position.id }">
				<c:out value="${ position.name }" />
			</option>
		</c:forEach>
--%>
	</select><br />

	<input type="submit" value="登録" /> <br />
<br />
</form>
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>
