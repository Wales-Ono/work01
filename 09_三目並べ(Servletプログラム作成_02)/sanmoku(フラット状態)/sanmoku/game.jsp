<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
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
<h2>[◯] のターン</h2>
[盤面]
<form action="/sanmoku/game" name="gameform" method="post">
<input type="hidden" name="cell">    
    <table border=1>
        <tr>
            <td><input type="button" name="cell0" value="<%= board.getCells(0) %>" onclick="pushCell(0)"></td>
            <td><input type="button" name="cell1" value="<%= board.getCells(1) %>" onclick="pushCell(1)"></td>
            <td><input type="button" name="cell2" value="<%= board.getCells(2) %>" onclick="pushCell(2)"></td>
        </tr>
    </table>
</form>
[/盤面]
<h2><a href="/sanmoku/title">タイトルに戻る</a></h2>
<script>
    function pushCell(i) {
        document.gameform.cell.value = i;
        // alert("座標（" + i +  "）");
        document.gameform.submit();
    }
</script>
</body>
</html>
