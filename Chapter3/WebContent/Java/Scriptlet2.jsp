<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>スクリプトレットのサンプル2</title>
</head>
<body>
<h3>スクリプトレットのサンプル2</h3>
<%
	int[] intArray = {1, 2, 3, 4, 5};
	for(int val : intArray) {
		out.println(val);
%>
	は、
<%
	if(val % 2 == 0) {
%>
	偶数です。
<%
	} else {
%>
	奇数です。
<%
	}
%>
	<br />
<%
	}
%>

</body>
</html>