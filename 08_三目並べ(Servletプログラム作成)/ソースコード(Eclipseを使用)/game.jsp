<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.Board" %>
<%
Board board = (Board)session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>三目並べ</title>
</head>
<body>
<h1>三目並べ</h1>
<h2>[◯] のターン</h2>
<% for(int i = 0;i < 9; i++){ %>
<form action="/sanmoku/GameServlet" method="get">
    <input type="hidden" name="btn" value="<%= i %>">
	<input type="submit" value="<%= board.getCells(i) %>">
</form>
<% } %>
<h2><a href="/sanmoku/TitleServlet">タイトルに戻る</a></h2>
</body>
</html>