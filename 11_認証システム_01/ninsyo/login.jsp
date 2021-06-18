<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>ログイン</h1><br>
<form action="/ninsyo/main" method="post">
    ID : <input type="text" name="inputId"><br>
    パスワード : <input type="password" name="inputPw"><br>
    <input type="submit" value="ログイン">
</form>
</body>
</html>