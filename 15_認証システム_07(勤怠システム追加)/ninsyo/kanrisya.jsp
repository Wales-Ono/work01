<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="model.User" %>
<% List<User> userList = (ArrayList<User>) request.getAttribute("userList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者権限画面</title>
<body>
<h1>管理者</h1>
<h2>◆ユーザー情報を編集または削除したいときは対応するものをクリックしてください</h2>
<table border=1>
    <tr>
        <th>名前</th><th>ID</th><th>パスワード</th><th>権限</th>
    </tr>
        <% for(User user : userList) { %>
            <tr>
                <td><%= user.getName() %></td>
                <td><%= user.getUserId() %></td>
                <td><%= user.getUserPw() %></td>
                <td><%= user.getAuth() %></td>
                <td><a href="/ninsyo/useredit?id=<%= user.getId() %>">編集</a></td>
                <td><a href="/ninsyo/userdel?id=<%= user.getId() %>">削除</a></td>
            </tr>
        <% } %>
</table>
<h2>◆新規ユーザーを追加するときは<a href="/ninsyo/useradd">こちら</a></h2>
<a href="/ninsyo/main">メインメニューへ</a><br>
</body>
</head>
</html>