<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<% User selectUser = (User) session.getAttribute("selectUser"); %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://ajaxzip3.github.io/ajaxzip3.js">
</script>
</head>
<meta charset="UTF-8">
<title>ユーザー編集画面</title>
<body>
<h1>ユーザー編集</h1>
<form action="/ninsyo/useredit" method="post">
    名前 :
    <input type="text" name="editName" value="<%= selectUser.getName() %>" required><br><br>
    ID : 
    <input type="text" name="editUserId" value="<%= selectUser.getUserId() %>" required><br><br>
    パスワード :
    <input type="text" name="editUserPw" value="<%= selectUser.getUserPw() %>" required><br><br>
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
    メールアドレス :
    <input type="text" name="editMailAddress" value="<%= selectUser.getMailAddress() %>" pattern="[\w\d_-]+@[\w\d_-]+\.[\w\d._-]+" title="書式が正しくありません" placeholder="(例) abc@gmail.com"><br><br>
    電話番号(携帯のみ)  :
    <input type="text" name="editPhoneNumber" value="<%= selectUser.getPhoneNumber() %>" size="14" pattern="^([0-9]{11})$" title="書式が正しくありません" placeholder="(例) 09012345678"><br><br>
    郵便番号  :
    <input type="text" name="zip11" value="<%= selectUser.getPostalCode() %>" size="8" onKeyUp="AjaxZip3.zip2addr(this,'','addr11','addr11');" pattern="^([0-9]{7})$" title="数字7桁で入力してください" placeholder="(例) 0123456"><br><br>
    住所1 :
    <input type="text" name="addr11" value="<%= selectUser.getAddress01() %>" size="40" pattern="^([^a-zA-Z0-9]{1,40})$" title="全角40文字以内で入力してください" placeholder="(例) 北海道札幌市中央区北四条西"><br><br>
    住所2 : 
    <input type="text" name="editAddress02" value="<%= selectUser.getAddress02() %>" size="40" pattern="^([^a-zA-Z0-9]{1,40})$" title="全角40文字以内で入力してください" placeholder="(例) 十二丁目１－２８"><br><br>
    編集後、次のボタンをクリックしてください。<br>
    <input type="submit" value="編集">
</form><br>
<a href="/ninsyo/main">メインメニューへ</a><br>
</body>
</head>
</html>