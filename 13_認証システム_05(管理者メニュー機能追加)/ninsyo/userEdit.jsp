<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<% User selectUser = (User) session.getAttribute("selectUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー編集画面</title>
<body>
<h1>ユーザー編集</h1>
<h2>◆編集前</h2>
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
</table>
<h2>◆編集後</h2>
<form action="/ninsyo/useredit" method="post">
    名前 :
    <input type="text" name="editName" required><br>
    ID : 
    <input type="text" name="editUserId" required><br>
    パスワード :
    <input type="password" name="editUserPw" required><br>
    権限 : 
    <select name="editAuthId">
        <option value="1">管理者</option>
        <option value="2">一般</option>
    </select><br><br>
    すべての項目入力後、次のボタンをクリックしてください。<br>
    <input type="submit" value="編集">
</form><br>
<a href="/ninsyo/main">メインメニューへ</a><br>
</body>
</head>
</html>