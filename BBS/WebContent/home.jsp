<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
<body>
<div class="main-contents">
	<h2>≪ホーム画面≫</h2>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${ errorMessages }" var="messages">
				<li><c:out value="${ messages }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<c:if test="${ not empty completeMessage }">
	<div class="completeMessage">
		<ul>
			<c:forEach items="${ completeMessage }" var="complet">
				<li><c:out value="${ complet }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="completeMessage" scope="session"/>
</c:if>

<div class="header">
	<h3>・メニュー</h3>
	<a href="posting">新規投稿</a>
	<a href="userControl">ユーザー管理</a>
	<a href="logout">ログアウト</a>
</div><br />

<h3>・投稿一覧</h3>
	<div class="userPostings">

	<c:forEach items="${ userPostings }" var="posting">
	<h4>-- 投稿内容 --</h4>
		<div class="name">【投稿者】：<c:out value="${ posting.name }"/></div>
		<div class="title">【タイトル】：<c:out value="${ posting.title }" /></div>
		<div class="text">【本文】：<c:out value="${ posting.text }" /></div>
		<div class="date">【投稿日時】：<fmt:formatDate value="${ posting.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /></div>
	 		<c:if test = "${ loginUser.branchId == 1 && loginUser.positionId == 2 }">
				<form action = "deletePosting" method = "post">
					<input type='hidden' value = "${ posting.postingId }" name='posting_id'>
					<input type = 'submit' value="投稿削除" onClick = "return confirm('この投稿を削除します。よろしいですか？')">
				</form>
			</c:if>
			<c:if test = "${ loginUser.branchId == posting.branchId
										&& loginUser.positionId == 3 && posting.positionId == 4 }">
				<form action = "deletePosting" method = "post">
					<input type='hidden' value = "${ posting.postingId }" name='posting_id'>
					<input type = 'submit' value="投稿削除" onClick = "return confirm('この投稿を削除します。よろしいですか？')">
				</form>
			</c:if><br />

		<div class="userComments">
		<h4>-- コメント内容 --</h4>
		<c:forEach items="${ userComments }" var="comment">
			<c:if test = "${ posting.postingId == comment.postingId }">
				<div class="name">【コメント投稿者】：<c:out value="${ comment.name }" /></div>
				<div class="text">【コメント本文】：<c:out value="${ comment.text }" /></div>
				<div class="date">【コメント投稿日時】：<fmt:formatDate value="${ comment.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /></div>
					<c:if test = "${ loginUser.branchId == 1 && loginUser.positionId == 2 }">
						<form action = "deleteComment" method = "post">
							<input type='hidden' value = "${ comment.commentId }" name='comment_id'>
							<input type = 'submit' value="コメント削除" onClick = "return confirm('このコメントを削除します。よろしいですか？')">
						</form>
					</c:if>
			</c:if><br />
		</c:forEach>

		<form action="comment" method="post">コメント入力<br />
			<textarea name="text" cols="50" rows="3" class="comment-box"></textarea><br />
			<input type="submit" value="コメント投稿"> (500文字以下)
			<input type='hidden' value="${ posting.postingId }" name='posting_id'>
		</form><br />
		<br />
		</div>
	</c:forEach>
</div><br />
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>