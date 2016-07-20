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
</div><br />
<HR width="70%" align="left">

<h3>・登録済みユーザー一覧</h3>
<div class="userControlList">

<style type="text/css">
	.table {
	  border-collapse: collapse;
	}
	.table th {
	  background-color: #cccccc;
	}
	.table td {
	  text-align: center;
	}
</style>

<table class = "table" border = 1>

	<tr>
		<th class="userId" width="100">ログインID</th>
		<th class="name" width="100">ユーザー名</th>
		<th class="name" width="100">所属支店</th>
		<th class="name" width="150">所属部署・役職</th>
		<th class="state" width="120">利用状況変更</th>
		<th class="delete" width="90">削除</th>
		<th class="setting" width="90">編集</th>
	</tr>

	<c:forEach items="${ userControlList }" var="user">
		<tr>
			<div class="account-name">
				<td><c:out value="${ user.userId }" /></td>
				<td><c:out value="${ user.name }" /></td>
				<c:forEach items="${ branchList }" var="branch">
					<c:if test = "${ user.branchId == branch.id }">
						<td><c:out value="${ branch.name }" /></td>
					</c:if>
				</c:forEach>

				<c:forEach items="${ positionList }" var="position">
					<c:if test = "${ user.positionId == position.id }">
						<td><c:out value="${ position.name }" /></td>
					</c:if>
				</c:forEach>
			</div>

			<td>
				<c:if test = "${ user.id != loginUser.id }">
					<form action="changeState" method="post">
						<c:if test = "${ user.state == 0 }">
							<input type='hidden' value="1" name='state'>
							<input type='hidden' value="${ user.id }" name='id'>
							<input type="submit" value="復活" onClick = "return confirm('このユーザーアカウントを復活します。よろしいですか？')">
			 			</c:if>

			 			<c:if test = "${ user.state == 1 }">
							<input type='hidden' value="0" name='state'>
							<input type='hidden' value="${ user.id }" name='id'>
							<input type="submit" value="停止" onClick = "return confirm('このユーザーアカウントを停止します。よろしいですか？')">
						</c:if>
					</form>
				</c:if>
			</td>

			<td>
				<c:if test = "${ user.id != loginUser.id }">
					<form action = "deleteUser" method = "post">
						<input type='hidden' value = "${ user.id }" name='id'>
						<input type = "submit" value="削除" onClick = "return confirm('このユーザーアカウントを削除します。よろしいですか？')">
					</form>
				</c:if>
			</td>

			<td><a href="settings?userId=${ user.id }">編集</a></td>

		</tr>
	</c:forEach>

</table>
<br />
</div>
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</body>
</html>