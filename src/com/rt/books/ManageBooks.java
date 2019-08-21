package com.rt.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ManageBooks {
String Url="jdbc:mariadb://localhost:3306/ramya";
String username="root";
String pass="password";
Connection conn;

	public void get_connection() throws SQLException {
		if(conn==null || conn.isClosed()) {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
			conn=DriverManager.getConnection(Url,username,pass);
			
		}
	}
		
public void disconnect() throws SQLException {
	if(conn!=null && !conn.isClosed())
		conn.close();
}
	
	
public boolean insert(Book book) throws SQLException {
String sql="insert into books values(?,?,?,?);";
get_connection();
PreparedStatement ps=conn.prepareStatement(sql);
ps.setString(1, book.getId());
ps.setString(2, book.getTitle());
ps.setString(3, book.getAuthor());
ps.setInt(4,book.getPrice());
boolean rowInserted = ps.executeUpdate() > 0;
ps.close();
disconnect();
return rowInserted;
}


public boolean deleteBook(Book book) throws SQLException {
    String sql = "DELETE FROM books where id = ?";
    get_connection();
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, book.getId());
    boolean rowDeleted = statement.executeUpdate() > 0;
    statement.close();
    disconnect();
    return rowDeleted;     
}
public List<Book> viewAllBooks() throws SQLException {
    List<Book> listBook = new ArrayList<>();
     
    String sql = "SELECT * FROM books";
     
    get_connection();
     
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
     
    while (resultSet.next()) {
        String id = resultSet.getString("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        int price = resultSet.getInt("price");
         
        Book book = new Book(id, title, author, price);
        listBook.add(book);
    }
     
    resultSet.close();
    statement.close();
     
    disconnect();
     
    return listBook;
}
public boolean updateBook(Book book) throws SQLException {
    String sql = "UPDATE books SET title = ?, author = ?, price = ?";
    sql += " WHERE id = ?";
    get_connection();
     
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, book.getTitle());
    statement.setString(2, book.getAuthor());
    statement.setInt(3, book.getPrice());
    statement.setString(4, book.getId());
     
    boolean rowUpdated = statement.executeUpdate() > 0;
    statement.close();
    disconnect();
    return rowUpdated;     
}
 
public Book getBook(String id) throws SQLException {
    Book book = null;
    String sql = "SELECT * FROM books WHERE id = ?";
     
    get_connection();
     
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, id);
     
    ResultSet resultSet = statement.executeQuery();
     
    if (resultSet.next()) {
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        int price = resultSet.getInt("price");
         
        book = new Book(id, title, author, price);
    }
     
    resultSet.close();
    statement.close();
     
    return book;
}


}
