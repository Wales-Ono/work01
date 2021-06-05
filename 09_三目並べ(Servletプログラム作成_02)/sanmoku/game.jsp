<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.Board" %>
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
<h2><%= board.getMark() %> のターン</h2>
<form action="/sanmoku/game" name="gameform" method="post">
<input type="hidden" name="cellNum">
    <table border=1>
            <tr>
                <td><input type="button" value="<%= board.getCells(0) %>" onclick="pushCell(0)"></td> 
                <td><input type="button" value="<%= board.getCells(1) %>" onclick="pushCell(1)"></td>
                <td><input type="button" value="<%= board.getCells(2) %>" onclick="pushCell(2)"></td>
            </tr>
            <tr>
                <td><input type="button" value="<%= board.getCells(3) %>" onclick="pushCell(3)"></td> 
                <td><input type="button" value="<%= board.getCells(4) %>" onclick="pushCell(4)"></td>
                <td><input type="button" value="<%= board.getCells(5) %>" onclick="pushCell(5)"></td>
            </tr>
            <tr>
                <td><input type="button" value="<%= board.getCells(6) %>" onclick="pushCell(6)"></td> 
                <td><input type="button" value="<%= board.getCells(7) %>" onclick="pushCell(7)"></td>
                <td><input type="button" value="<%= board.getCells(8) %>" onclick="pushCell(8)"></td>
            </tr>
    </table>
</form>

<h2><a href="/sanmoku/title">タイトルに戻る</a></h2>

<script>
    function pushCell(i) {
        document.gameform.cellNum.value = i;
        document.gameform.submit();
    }
</script>

</body>
</html>