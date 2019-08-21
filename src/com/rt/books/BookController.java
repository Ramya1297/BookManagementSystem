package com.rt.books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	 private void listBook(HttpServletRequest request, HttpServletResponse response)
	       throws SQLException, IOException, ServletException {
		 ManageBooks mb=new ManageBooks();
	        List<Book> listBook = mb.viewAllBooks();
	   
	        response.setContentType("text/html;charset=UTF-8");
			PrintWriter pr=response.getWriter();
			pr.print("\n");
	        for(Book e : listBook) {
	        	pr.println(e.getId()+" "+e.getTitle()+" "+e.getAuthor()+" "+e.getPrice()+"\n");
	        	
	        }
	    }
	 
	   
	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	    	 ManageBooks mb=new ManageBooks();
	        String id = request.getParameter("id");
	        Book e = mb.getBook(id);
	       // RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
	        //request.setAttribute("book", existingBook);
	        //dispatcher.forward(request, response);
	        response.setContentType("text/html;charset=UTF-8");
			PrintWriter pr=response.getWriter();
			pr.println("id title author price <br>");
			pr.println(e.id+" "+e.title+" "+e.author+" "+e.price+"<br>");
	 
	    }
	    private void showEditForm1(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	    	 ManageBooks mb=new ManageBooks();
	        String id = request.getParameter("id");
	        Book e = mb.getBook(id);
            response.setContentType("text/html;charset=UTF-8");
			PrintWriter pr=response.getWriter();
			pr.println("id title author price <br>");
			pr.println(e.id+" "+e.title+" "+e.author+" "+e.price+"<br>");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("edit1.jsp");
	        //request.setAttribute("book", e);
	       // dispatcher.forward(request, response);
			request.setAttribute("book", e);
			request.getRequestDispatcher("edit1.jsp").forward(request, response);
	 
	    }
	 
	    private void insertBook(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	    	ManageBooks mb=new ManageBooks();
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        String id = request.getParameter("id");
	        int price = Integer.parseInt(request.getParameter("price"));
	        Book newBook = new Book(id,title, author, price);
	        boolean st=mb.insert(newBook);
	        response.setContentType("text/html;charset=UTF-8");
			PrintWriter pr=response.getWriter();
	       if(st==true)
	    	   pr.print("Successfully added");
	       else
	    	   pr.print("Something went wrong");
	    }
	 
	    private void updateBook(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	    	ManageBooks mb=new ManageBooks();
	       String id = request.getParameter("id");
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	       int price = Integer.parseInt(request.getParameter("price"));
	 
	        Book book = new Book(id, title, author, price);
	        boolean st=mb.updateBook(book);
	        response.setContentType("text/html;charset=UTF-8");
			PrintWriter pr=response.getWriter();
	       if(st==true)
	    	   pr.print("Successfully updated");
	       else
	    	   pr.print("Something went wrong");
	 
	       // response.sendRedirect("list");
	    }
	 
	    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        String id = request.getParameter("id");
	        ManageBooks mb=new ManageBooks();
	        Book book = new Book(id);
	       boolean st= mb.deleteBook(book);
	     //   response.sendRedirect("list");
	        response.setContentType("text/html;charset=UTF-8");
			PrintWriter pr=response.getWriter();
	       if(st==true)
	    	   pr.print("Successfully deleted");
	       else
	    	   pr.print("Something went wrong");
	 
	    }
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String action = request.getServletPath();
		String r = request.getParameter("button");
		try {
		if(r.equals("add")) {
			 insertBook(request, response);
		}
		else if(r.equals("del")) {
			 deleteBook(request, response);
		}
		else if(r.equals("edit")) {
			updateBook(request, response);
		}
		else if(r.equals("view")) {
			showEditForm(request, response);
		}
		else if(r.equals("edit1")) {
			showEditForm1(request, response);
		}
		else {
			listBook(request,response);
		}
		}
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
	
}
