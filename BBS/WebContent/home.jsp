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

<h2>
	<c:out value="${ loginUser.name }"></c:out>
	さん、こんにちは。
</h2>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${ errorMessages }" var="messages">
				<font color="red"><li>エラー：<c:out value="${ messages }" /></font>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<c:if test="${ not empty completeMessage }">
	<div class="completeMessage">
		<ul>
			<c:forEach items="${ completeMessage }" var="complet">
				<font color="green">成功：<c:out value="${ complet }" /></font>
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
<HR width="60%" align="left">

<h3>・検索</h3>
<form action="home" method="get">
	<table class="posting_select">
		<tr>
			<td>カテゴリー<br>
			<select name="category">
			<option value="">全件表示</option>
				<c:forEach items="${ categories }" var="posting">
					<option value="${ posting.category }"<c:if test="${ posting.category == category }">selected</c:if>>
						<c:out value="${ posting.category }" />
					</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>投稿日(期間指定)<br>
			<select name="startYear">
			<c:forEach begin="2016" end="2025"  varStatus="status">
				<option value="${ status.index }" <c:if test="${ status.index == startYear }">selected</c:if>>
					<c:out value="${ status.index }"/>
				</option>
			</c:forEach>
			</select>年

			<select name="startMonth">
			<c:forEach begin="01" end="12"  varStatus="status">
				<option value="${ status.index }" <c:if test="${ status.index == startMonth }">selected</c:if>>
					<c:out value="${ status.index }"/>
				</option>
			</c:forEach>
			</select>月

			<select name="startDay">
			<c:forEach begin="01" end="31"  varStatus="status">
				<option value="${ status.index }" <c:if test="${ status.index == startDay }">selected</c:if>>
					<c:out value="${ status.index }"/>
				</option>
			</c:forEach>
			</select>日
			 ～
			<select name="endYear">
			<c:forEach begin="2016" end="2025"  varStatus="status">
				<option value="${ status.index }"  <c:if test="${ status.index == endYear }">selected</c:if>>
					<c:out value="${ status.index }"/>
				</option>
			</c:forEach>
			</select>年

			<select name="endMonth">
			<c:forEach begin="01" end="12"  varStatus="status">
				<option value="${ status.index }" <c:if test="${ status.index == endMonth }">selected</c:if>>
					<c:out value="${ status.index }"/>
				</option>
			</c:forEach>
			</select>月

			<select name="endDay">
			<c:forEach begin="01" end="31"  varStatus="status">
				<option value="${ status.index }"<c:if test="${ status.index == endDay }">selected</c:if>>
					<c:out value="${ status.index }"/>
				</option>
			</c:forEach>
			</select>日
			<br>
			<br />
			<input type="submit" value="検索">
			<button type="button"onclick="location.href='home'">リセット</button>
		</tr>
	</table>
</form><br/>
<HR width="60%" align="left">

<h3>・投稿一覧</h3>
	<div class="userPostings">

<c:forEach items="${ userPostings }" var="posting">
	<h4>-- 投稿内容 --</h4>
		<div class="name">【投稿者】：<c:out value="${ posting.name }"/></div>
		<div class="title">【件名】：<c:out value="${ posting.title }" /></div>
		<div class="text">【本文】：

		<%
		String lineSeparator = System.getProperty("line.separator");
		String result = "text".replaceAll(lineSeparator, "<br>");
		%>

		<c:forTokens var="splitPosting" items="${ posting.text }" delims="<%= lineSeparator %>">
			<c:out value="${ splitPosting }"></c:out>
			<br>
		</c:forTokens>

		</div>
		<div class="category">【カテゴリー】：<c:out value="${ posting.category }" /></div>
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
			<form action = "deleteComment" method = "post">
				<c:if test = "${ loginUser.branchId == 1 && loginUser.positionId == 2 }">
					<input type='hidden' value = "${ comment.commentId }" name='comment_id'>
					<input type = 'submit' value="コメント削除" onClick = "return confirm('このコメントを削除します。よろしいですか？')">
				</c:if>
				<c:if test = "${ loginUser.branchId == comment.branchId
									&& loginUser.positionId == 3 && comment.positionId == 4 }">
					<input type='hidden' value = "${ comment.commentId }" name='comment_id'>
					<input type = 'submit' value="コメント削除" onClick = "return confirm('このコメントを削除します。よろしいですか？')">
				</c:if>
			</form>
		</c:if><br />
	</c:forEach>

	<form action="comment" method="post">コメント入力<br />
		<textarea name="text" cols="50" rows="3" class="comment-box"></textarea><br />
		<input type="submit" value="コメント投稿"> (500文字以下)
		<input type='hidden' value="${ posting.postingId }" name='posting_id'>
	</form><br />
	<br />
	</div>
	<HR width="60%" align="left">
</c:forEach>
</div><br />
<div class="copyright">Copyright(c) Haruka Suganuma</div>
</div>
</body>
</html>