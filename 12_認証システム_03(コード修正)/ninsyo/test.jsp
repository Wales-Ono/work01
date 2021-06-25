<%@ page language="java" contentType="text/html; chatset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@page
import="java.sql.*,
         javax.sql.*,
         javax.naming.InitialContext,
         javax.naming.Context"%>
<title>memberlist.jsp実行結果</title>
</head>

<body>
<%
  //TomcatからInitialContextを取得します。
  InitialContext initctx = new InitialContext();
  //JNDIルックアップを行いデータソースを取得します。
  //TomcatのJNDIソースはすべてjava:comp/env/への相対パスとなります。
  DataSource ds = (DataSource)initctx.lookup("java:comp/env/jdbc/NinsyoDB");
  //データソースからコネクションを取得します。
  Connection con = ds.getConnection();

  Statement stmt = con.createStatement();
  //SQLの結果セットをResultSetオブジェクトに格納します。
  ResultSet rs = stmt.executeQuery("select * from user;");
%>
<h1>ユーザーリスト</h1>
<table>
<tr>
   <th>ID</th>
   <th>ユーザーID</th>
   <th>パスワード</th>
   <th>名前</th>
   <th>権限</th>
</tr>
<% while(rs.next()) { %>
<tr>
   <td>
   <%= rs.getString("id") %>
   </td>
   <td>
    <%= rs.getString("user_id") %>
    </td>
   <td>
   <%= rs.getString("user_pw") %>
   </td>
   <td>
   <%= rs.getString("name") %>
   </td>
   <td>
   <%= rs.getString("auth") %>
   </td>
</tr>
<%
  }
  con.close();
  initctx.close();
%>
</table>
</body>
</html>