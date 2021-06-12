<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.Board" %>
<%@ page import="controller.GameServlet" %>
<%
Board board = (Board) session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>三目並べ</title>
</head>
<body>
<h1>三目並べ</h1>
<h2>引き分けです!</h2>
    <table border=1>
            <tr>
                <td><input type="button" value="<%= board.getCells(0) %>"></td> 
                <td><input type="button" value="<%= board.getCells(1) %>"></td>
                <td><input type="button" value="<%= board.getCells(2) %>"></td>
            </tr>
            <tr>
                <td><input type="button" value="<%= board.getCells(3) %>"></td> 
                <td><input type="button" value="<%= board.getCells(4) %>"></td>
                <td><input type="button" value="<%= board.getCells(5) %>"></td>
            </tr>
            <tr>
                <td><input type="button" value="<%= board.getCells(6) %>"></td> 
                <td><input type="button" value="<%= board.getCells(7) %>"></td>
                <td><input type="button" value="<%= board.getCells(8) %>"></td>
            </tr>
    </table>
</form>
<h2><a href="/sanmoku/title">タイトルに戻る</a></h2>
</body>
</html>