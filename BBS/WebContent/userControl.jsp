<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
	<h2>≪ユーザー管理≫</h2>
</div>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${ errorMessages }" var="message">
				<font color="red"><li>エラー：<c:out value="${ message }" /></font>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<c:if test="${ not empty completeMessage }">
	<div class="completeMessage">
		<ul>
			<c:forEach items="${ completeMessage }" var="complet">
				<font color="green"><li>成功：<c:out value="${ complet }" /></font>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="completeMessage" scope="session"/>
</c:if>
	<c:remove var="editUser" scope="session"/>

<div class="header">
	<h3>・メニュー</h3>
	<a href="home">ホーム画面</a>
	<a href="signup">ユーザー新規登録</a>
</div>
<br />

<h3>・登録済みユーザー一覧</h3>
<div class="userControlList">
<table>
	<c:forEach items="${ userControlList }" var="user">
		<div class="setting">
			<div class="account-name">
				<span class="userId">【ログインID】：<c:out value="${ user.userId }" /></span><br/>

				<span class="name">【ユーザー名】：<c:out value="${ user.name }" /></span><br/>

				<c:forEach items="${ branchList }" var="branch">
					<c:if test = "${ user.branchId == branch.id }">
						<span class="name">【所属支店】：<c:out value="${ branch.name }" /></span><br/>
					</c:if>
				</c:forEach>

				<c:forEach items="${ positionList }" var="position">
					<c:if test = "${ user.positionId == position.id }">
						<span class="name">【所属部署・役職】：<c:out value="${ position.name }" /></span><br/>
					</c:if>
				</c:forEach>
			</div>
			<c:if test = "${ user.id != loginUser.id }">
				<form action="changeState" method="post">
					<c:if test = "${ user.state == 0 }">
						<input type='hidden' value="1" name='state'>
						<input type='hidden' value="${ user.id }" name='id'>
						<input type="submit" value="アカウントを復活" onClick = "return confirm('このユーザーアカウントを復活します。よろしいですか？')">
		 			</c:if>
		 			<c:if test = "${ user.state == 1 }">
						<input type='hidden' value="0" name='state'>
						<input type='hidden' value="${ user.id }" name='id'>
						<input type="submit" value="アカウントを停止" onClick = "return confirm('このユーザーアカウントを停止します。よろしいですか？')">
					</c:if>
				</form>
			</c:if>
			<c:if test = "${ user.id != loginUser.id }">
				<form action = "deleteUser" method = "post">
					<input type='hidden' value = "${ user.id }" name='id'>
					<input type = "submit" value="アカウント削除" onClick = "return confirm('このユーザーアカウントを削除します。よろしいですか？')">
				</form></br>
			</c:if>
			<a href="settings?userId=${ user.id }">ユーザー編集</a><br/>
			<br />
		</div>
	</c:forEach>
</table>
</div>
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</body>
</html>