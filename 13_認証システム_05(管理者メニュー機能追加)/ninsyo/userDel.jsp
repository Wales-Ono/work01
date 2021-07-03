<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<% User selectUser = (User) session.getAttribute("selectUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除画面</title>
<body>
<h1>ユーザー削除</h1>
<table border="1">
    <tr>
        <th>名前</th><th>ID</th><th>パスワード</th><th>権限</th>
    <tr>
        <td><%= selectUser.getName() %></td>
        <td><%= selectUser.getUserId() %></td>
        <td><%= selectUser.getUserPw() %></td>
        <td><%= selectUser.getAuth() %></td>
    </tr>
    </tr>
</table><br>
上記のユーザーを削除するときは次のボタンをクリックしてください
<form action="/ninsyo/userdel" method="post">
    <input type="submit" value="削除">
</form><br>
<a href="/ninsyo/main">メインメニューへ</a><br>
</body>
</head>
</html>