package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public AddToCart() {
	      super();
	   }
	
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String bookTitle = request.getParameter("title");
	      String username = request.getParameter("username");
	      Double price = Double.valueOf(request.getParameter("price"));

	      Connection connection = null;
	      String insertSql = " INSERT INTO USER_BOOKS (USERNAME, BOOK_TITLE, PRICE) values (?, ?, ?)";
	      
 

	      try {
	         DBConnectionDjossou.getDBConnection();
	         connection = DBConnectionDjossou.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
	         preparedStmt.setString(1, username);
	         preparedStmt.setString(2, bookTitle);
	         preparedStmt.setDouble(3, price);
	         
	         preparedStmt.execute();
	         
		   	
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Successfully Added to Cart";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" + //
	            "<ul>\n" + //

	            "  <li><b>Book Title</b>: " + bookTitle + "\n" + //
	            "  <li><b>Price</b>: " + price + "\n" + //
	            "  <li><b>Username</b>: " + username + "\n" + //
	  

	            "</ul>\n");

	      out.println("<a href=/Bookstore-Project/cart.html id=\"login\">CheckOut</a> <br>");
	      out.println("<a href=/Bookstore-Project/index.html id=\"login\">Go Home</a> <br>");
	      out.println("</body></html>");
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doGet(request, response);
	   }

}
