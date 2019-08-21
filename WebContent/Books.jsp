<%@page language="java" contentType="text/html; charset=ISO-8859-1" import="com.rt.books.ManageBooks,java.io.PrintWriter,java.util.List,com.rt.books.Book" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
      
            <%
            ManageBooks mb=new ManageBooks();
	        List<Book> listBook = mb.viewAllBooks();
	        response.setContentType("text/html;charset=UTF-8");
			PrintWriter pr=response.getWriter();
			pr.println("id title author price <br>");
			
	        for(Book e : listBook) {
	        	pr.println("<br>"+e.getId()+" "+e.getTitle()+" "+e.getAuthor()+" "+e.getPrice()+"<br>");
	        	
	        }
	        %>
           
    </div>   
</body>
</html>