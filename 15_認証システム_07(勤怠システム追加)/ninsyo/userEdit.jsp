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
<form action="/ninsyo/useredit" method="post">
    名前 :
    <input type="text" name="editName" value="<%= selectUser.getName() %>" required><br>
    ID : 
    <input type="text" name="editUserId" value="<%= selectUser.getUserId() %>" required><br>
    パスワード :
    <input type="text" name="editUserPw" value="<%= selectUser.getUserPw() %>" required><br>
    権限 : 
    <select name="editAuthId">
        <% if (selectUser.getAuthId() == 1) { %>
            <option value="1" selected>管理者</option>
            <option value="2">一般</option>
        <% } else { %>
            <option value="1">管理者</option>
            <option value="2" selected>一般</option>
        <% } %>
    </select><br><br>
    編集後、次のボタンをクリックしてください。<br>
    <input type="submit" value="編集">
</form><br>
<a href="/ninsyo/main">メインメニューへ</a><br>
</body>
</head>
</html>