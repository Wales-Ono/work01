<%@ page language="java" contentType="text/html; chatset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Attendance" %>
<% List<Attendance> attendanceList = (ArrayList<Attendance>) session.getAttribute("attendanceList"); %>
<% Attendance attendanceToday = (Attendance) session.getAttribute("attendanceToday"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メイン画面</title>
<script>
    function pushBtn(i) {
        document.punchform.btn.value = i;
        document.punchform.submit();
    }
</script>
</head>
<body>
<h1>メインメニュー</h1><br><br>
<form action="/ninsyo/main" method="get" name="punchform">
    <input type="hidden" name="btn">
    <% if (attendanceToday == null) { %>
        <input type="button" value="出勤" id="begin" onclick="pushBtn(1)">
        <input type="button" value="退勤" id="end" disabled>
    <% } else { %>
        <% if (attendanceToday.getBegin() == null) { %>
            <input type="button" value="出勤" id="begin" onclick="pushBtn(1)">
            <input type="button" value="退勤" id="end" disabled>
        <% } else {  %>
            <% if (attendanceToday.getEnd() == null) { %>
                <input type="button" value="出勤" id="begin" disabled>
                <input type="button" value="退勤" id="end" onclick="pushBtn(2)">
            <% } else {  %>
                <input type="button" value="出勤" id="begin" disabled>
                <input type="button" value="退勤" id="end" disabled>
            <% } %>
        <% } %>            
    <% } %>
</form>
<h3>◆勤務一覧</h3>
<table border=1>
    <tr>
        <th>日付</th><th>出勤時刻</th><th>退勤時刻</th><th>勤務時間</th><th>休憩時間</th>
    </tr>
        <% for (Attendance attendance : attendanceList) { %>
            <tr>
                <td><%= attendance.getDate() %></td>
                <td><%= attendance.getBegin() %></td>
                <td><%= attendance.getEnd() %></td>
                <td><%= attendance.getWorktime() %></td>
                <td><%= attendance.getRest() %></td>
            </tr>
        <% } %>
</table><br><br><hr>
一般メニューは<a href="/ninsyo/kengen?action=ippan">こちら</a>をクリック<br>
管理者メニューは<a href="/ninsyo/kengen?action=kanrisya">こちら</a>をクリック<br><br>
<a href="/ninsyo/logout">ログアウト</a>
</body>
</head>
</html>