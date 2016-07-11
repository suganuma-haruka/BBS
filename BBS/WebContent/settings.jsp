<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
	<h2>≪ユーザー編集≫</h2>
</div>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="messages">
				<li><c:out value="${ messages }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="settings" method="post" >

	<label for="userId">ユーザーID</label><br />
	<input name="userId" value="${ editUser.userId }" id="userId"/>(半角英数字、6文字以上20文字以内)<br />

	<label for="password">パスワード</label><br />
	<input name="password" type="password"  id="password" >(記号を含む全ての半角文字、6文字以上255文字以内)<br />

  	<label for="passwordCheck">パスワード（確認）</label><br />
  	<input name="passwordCheck" type="password" id="password"/><br />

  	<label for="name">ユーザー名</label><br />
	<input name="name" value="${ editUser.name }" id="name"/>(10文字以内) <br />

	<c:if test = "${ editUser.id != loginUser.id }">
		<label for = "branch">所属支店</label><br />
		 	<select name="branch">
				<c:forEach items="${ branchList }" var="branch">
					<option value="${ branch.id }">${ branch.name }</option>
				</c:forEach>
			</select>
	</c:if>
<br />

	<c:if test = "${ editUser.id != loginUser.id }">
		<label for = "position">所属部署・役職</label><br />
			<select name="position">
				<c:forEach items="${ positionList }" var="position">
					<option value="${ position.id }">${ position.name }</option>
				</c:forEach>
			</select><br />
	</c:if>
	<input type='hidden' value="${ editUser.id }" name='id'>
	<input type="submit" value="編集" />
	</form>
<br />
<div class="header">
	<a href="userControl">戻る</a><br />
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>