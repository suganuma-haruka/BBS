<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルアップロードのサンプル</title>
</head>
<body>
<h3>ファイルアップロードのサンプル</h3>

<form action="file_upload" method="post" enctype="multipart/form-data">
<input type="file" name="content"> <br />
<input type="submit" /> <br />
</form>

<br />

</body>
</html>