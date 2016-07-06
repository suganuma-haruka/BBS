<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム画面</title>
</head>
<body>
<div class="main-contents">
<h3>ホーム画面</h3>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="messages">
				<li><c:out value="${messages}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<div class="header">
	<a href="posting">新規投稿</a>
	<a href="signup">ユーザー新規登録</a>
	<a href="logout">ログアウト</a>
</div>
<br />


<h3>投稿一覧</h3>
	<div class="userPostings">
	<c:forEach items="${ userPostings }" var="posting">
		<div class="name">投稿者：<c:out value="${ posting.name }"/></div>
		<div class="title">タイトル：<c:out value="${ posting.title }" /></div>
		<div class="text">本文：<c:out value="${ posting.text }" /></div>
		<div class="date">投稿日時：<fmt:formatDate value="${ posting.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /></div><br />

		<div class="userComments">
		<c:forEach items="${ userComments }" var="comment">
			<c:if test = "${ posting.postingId == comment.postingId }">
				<div class="name">コメント投稿者：<c:out value="${comment.name }" /></div>
				<div class="comment">コメント本文：<c:out value="${ comment.text }" /></div>
				<div class="date">コメント投稿日時：<fmt:formatDate value="${ comment.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /></div><br />
			</c:if>
		</c:forEach>

		<form action="comment" method="post">コメント入力<br />
			<textarea name="text" cols="50" rows="5" class="comment-box"></textarea><br />
			<input type="submit" value="コメント投稿">(500文字まで)
			<input type='hidden' value="${ posting.postingId }" name='posting_id'>
		</form>
		<br />
		</div>
	</c:forEach>

</div>
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>