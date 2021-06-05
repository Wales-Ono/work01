<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.Board" %>
<%@ page import="model.GameLogic" %>
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
            <td><input type="button" value="<%= board.getCells0() %>" onclick="pushCell(0)"></td> 
            <td><input type="button" value="<%= board.getCells1() %>" onclick="pushCell(1)"></td>
            <td><input type="button" value="<%= board.getCells2() %>" onclick="pushCell(2)"></td>
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
