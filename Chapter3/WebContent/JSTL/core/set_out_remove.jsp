<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>set,out,removeのサンプル</title>
</head>
<body>
	<h3>set,out,removeのサンプル</h3>

	<c:set var="str" value="Hello, core!" />
	<%-- strにHello, core!をセット --%>

	<c:out value="${str}" />
	<%-- strを出力 --%>
	<br />

	<c:remove var="str" />
	<%-- strを削除 --%>

	<c:out value="${str}" default="指定した変数が殻の場合に表示されるデフォルトの文字列です" />
	<%-- 削除されたstrを出力 --%>
	<br />

	<%-- JavaScreptを出力 --%>
	<c:set var="str"
		value="<script type='text/javascript'> alert('Hello, core!'); </script>" />
	<c:out value="${str}" />
	<br />
	<!-- escapeXml属性省略 -->
	<c:out value="${str}" escapeXml="false" />
	<br />
	<!-- escapeXml=false -->
	<br />

</body>
</html>