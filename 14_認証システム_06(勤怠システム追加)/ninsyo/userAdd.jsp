<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録画面</title>
<body>
<h1>ユーザー追加</h1>
<form action="/ninsyo/useradd" method="post">
    名前 :
    <input type="text" name="addName" required><br>
    ID : 
    <input type="text" name="addUserId" required><br>
    パスワード :
    <input type="password" name="addUserPw" required><br>
    権限 : 
    <select name="addAuthId">
        <option value="1">管理者</option>
        <option value="2">一般</option>
    </select><br><br>
    すべての項目入力後、次のボタンをクリックしてください。<br>
    <input type="submit" value="追加">
</form><br>
<a href="/ninsyo/main">メインメニューへ</a><br>
</body>
</head>
</html>