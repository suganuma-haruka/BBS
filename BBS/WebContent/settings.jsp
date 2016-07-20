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
				<font color="red"><li>エラー：<c:out value="${ messages }" /></font>
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

<h3>・ユーザー編集</h3>
<form action="settings" method="post" >

	<label for="userId">ログインID</label><br />
	<input name="userId" value="${ editUser.userId }" id="userId"/>(半角英数字、6文字以上20文字以内)<br />

	<label for="password">パスワード</label><br />
	<input name="password" type="password"  id="password" >(記号を含む全ての半角文字、6文字以上255文字以内)<br />

  	<label for="passwordCheck">パスワード（確認）</label><br />
  	<input name="passwordCheck" type="password" id="password"/><br />

  	<label for="name">ユーザー名</label><br />
	<input name="name" value="${ editUser.name }" id="name"/>(10文字以内)<br />


	<label for = "branch">所属支店</label><br />
	 	<select name="branch">
 		<%--<option value="0">--選択してください--</option>--%>
 			<c:if test = "${ editUser.id == loginUser.id }">
				<option value = "1"><c:out value="本社" /></option>
				<%--<input type='hidden' value = "${ branch.id }" name="branch"> --%>
			</c:if>

			<c:forEach items="${ branchList }" var="branch">
				<c:if test = "${ editUser.id != loginUser.id }">
					<option value="${ branch.id }" <c:if test="${ branch.id == editUser.branchId }">selected</c:if> >
						<c:out value="${ branch.name }" />
					</option>
				</c:if>
			</c:forEach>
		</select><br />

	<label for = "position">所属部署・役職</label><br />
		<select name="position">
		<%--<option value="0">--選択してください--</option>--%>
			<c:if test = "${ editUser.id == loginUser.id }">
				<option value = "1"><c:out value="人事総務部" /></option>
				<%--<input type='hidden' value = "${ position.id }" name="position"> --%>
			</c:if>

			<c:forEach items="${ positionList }" var="position">
				<c:if test = "${ editUser.id != loginUser.id }">
					<option value="${ position.id }" <c:if test="${ position.id == editUser.positionId }">selected</c:if> >
						<c:out value="${ position.name }" />
					</option>
				</c:if>
			</c:forEach>
		</select><br />

	<input type='hidden' value="${ editUser.id }" name='id'>
	<br />
	<input type="submit" value="編集" />
	</form>
<br />
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</body>
</html>