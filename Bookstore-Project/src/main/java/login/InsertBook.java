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

@WebServlet("/Insert")
public class InsertBook extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	   public InsertBook() {
	      super();
	   }
	   
	   
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      String booktitle = request.getParameter("title");
		      String fName = request.getParameter("fName");
		      String lName = request.getParameter("lName");
		      Double price = Double.parseDouble(request.getParameter("price"));
		      String isbn = request.getParameter("isbn");
		      int stock = Integer.parseInt(request.getParameter("stock"));
		      String image = request.getParameter("image_url");
		      int rating = Integer.parseInt(request.getParameter("rating"));
		      String desc = request.getParameter("description");

		      Connection connection = null;
		      String insertSql = " INSERT INTO Books (id, BOOK_TITLE, AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME, PRICE, ISBN, STOCK, IMAGE_URL, RATING, DESCRIPTION) values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		      try {
		         DBConnectionDjossou.getDBConnection();
		         connection = DBConnectionDjossou.connection;
		         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
		         preparedStmt.setString(1, booktitle);
		         preparedStmt.setString(2, fName);
		         preparedStmt.setString(3, lName);
		         preparedStmt.setDouble(4, price);
		         preparedStmt.setString(5, isbn);
		         preparedStmt.setInt(6, stock);
		         preparedStmt.setString(7, image);
		         preparedStmt.setInt(8, rating);
		         preparedStmt.setString(9, desc);
		         
		         preparedStmt.execute();
		         connection.close();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }

		      // Set response content type
		      response.setContentType("text/html");
		      PrintWriter out = response.getWriter();
		      String title = "Added Book to Database";
		      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		      out.println(docType + //
		            "<html>\n" + //
		            "<head><title>" + title + "</title></head>\n" + //
		            "<body bgcolor=\"#f0f0f0\">\n" + //
		            "<h2 align=\"center\">" + title + "</h2>\n" + //
		            "<ul>\n" + //

		            "  <li><b>Book Title</b>: " + booktitle + "\n" + //
		            "  <li><b>Author First Name</b>: " + fName + "\n" + //
		            "  <li><b>Author Last Name</b>: " + lName + "\n" + //
		            "  <li><b>Price</b>: " + price + "\n" + //
		            "  <li><b>ISBN</b>: " + isbn + "\n" + //
		            "  <li><b>Stock</b>: " + stock + "\n" + //
		            "  <li><b>Rating</b>: " + rating + "\n" + //
		            "  <li><b>Description</b>: " + desc + "\n" + //
		            "  <li><b>Image Link</b>: " + image + "\n" + //
		  

		            "</ul>\n");

		      out.println("<a href=/Bookstore-Project/insert.html  id=\"insert\" >Add Another Book</a> <br>");
		      out.println("</body></html>");
		   }

		   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      doGet(request, response);
		   }

}
