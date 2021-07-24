<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録画面</title>
<script type="text/javascript" src="https://ajaxzip3.github.io/ajaxzip3.js">
</script>
</head>
<body>
<h1>ユーザー追加</h1>
<form name="inputform" action="/ninsyo/useradd" method="post">
    名前 :
    <input type="text" name="addName" pattern="^([^a-zA-Z0-9]{1,15})$" title="全角15文字以内で入力してください" placeholder="(例) 山田太郎" required><br><br>
    ID  : 
    <input type="text" name="addUserId" pattern="^([a-zA-Z0-9]{1,15})$" title="半角英数字15文字以内で入力してください" placeholder="(例) yamadataro" required><br><br>
    パスワード  :
    <input type="password" name="addUserPw" pattern="^([a-zA-Z0-9]{1,15})$" title="半角英数字15文字以内で入力してください" placeholder="(例) taro123" required><br><br>
    権限 : 
    <select name="addAuthId">
        <option value="1">管理者</option>
        <option value="2">一般</option>
    </select><br><br>
    メールアドレス :
    <input type="text" name="addMailAddress" pattern="[\w\d_-]+@[\w\d_-]+\.[\w\d._-]+" title="書式が正しくありません" placeholder="(例) abc@gmail.com" required><br><br>
    電話番号(携帯のみ)  :
    <input type="text" name="addPhoneNumber" size="14" pattern="^([0-9]{11})$" title="書式が正しくありません" placeholder="(例) 09012345678" required><br><br>
    郵便番号  :
    <input type="text" name="zip11" size="8" onKeyUp="AjaxZip3.zip2addr(this,'','addr11','addr11');" pattern="^([0-9]{7})$" title="数字7桁で入力してください" placeholder="(例) 0600004" required><br><br>
    住所1 :
    <input type="text" name="addr11" size="40" pattern="^([^a-zA-Z0-9]{1,40})$" title="全角40文字以内で入力してください" placeholder="(例) 北海道札幌市中央区北四条西" required><br><br>
    住所2 (任意) : 
    <input type="text" name="addAddress02" size="40" pattern="^([^a-zA-Z0-9]{1,40})$" title="全角40文字以内で入力してください" placeholder="(例) 十二丁目１－２８"><br><br>
    入力後、次のボタンをクリックしてください。<br><br>
    <input type="submit" value="追加">
</form><br>
<a href="/ninsyo/main">メインメニューへ</a><br>
</body>
</head>
</html>