<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.rt.books.*,java.io.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String id=request.getParameter("id");
//out.println(id);
ManageBooks mb=new ManageBooks();
Book e = mb.getBook(id);
response.setContentType("text/html;charset=UTF-8");
PrintWriter pr=response.getWriter();
pr.println("id title author price <br>");
pr.println(e.getId()+" "+e.getTitle()+" "+e.getAuthor()+" "+e.getPrice()+"<br>");
%>

<form name="edit1" action="BookController" method="get">
id<input type='text' name="id"><br>
title<input type='text' name='title'><br>
author<input type='text' name='author'><br>
price<input type='text' name='price'><br>

<input type="submit" value="edit" name="button" >
</form>

</body>
</html>