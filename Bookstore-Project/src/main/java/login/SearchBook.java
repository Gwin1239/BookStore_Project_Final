package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class SearchBook extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	   public SearchBook() {
	      super();
	   }
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookTitle = request.getParameter("title");
		
		String fName = request.getParameter("fName");
		
		String lName = request.getParameter("lName");
		
		String rating = request.getParameter("rating");
		
		
		
		
		System.out.println(request.getContextPath() + request.getServletPath() + request.getPathInfo());
		System.out.println(bookTitle);
		
		Connection connection = null;
		try {
			DBConnectionDjossou.getDBConnection();
	           connection = DBConnectionDjossou.connection;
	           PreparedStatement preparedStmt = null;
	           
	           if (bookTitle.isEmpty() && fName.isEmpty() && lName.isEmpty() && rating.isEmpty()) {
	              System.out.println("Inputted book DNE!");
	              String bookSelectSQL ="SELECT * FROM Books";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	              
	            } 
	           
	           else if (!bookTitle.isEmpty() && fName.isEmpty() && lName.isEmpty() && rating.isEmpty()) {
	        	   String bookSelectSQL ="SELECT * FROM Books WHERE BOOK_TITLE = ?";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	               preparedStmt.setString(1, bookTitle);
	           }
	           
	           else if (!bookTitle.isEmpty() && !fName.isEmpty() && lName.isEmpty() && rating.isEmpty()) {
	        	   String bookSelectSQL ="SELECT * FROM Books WHERE BOOK_TITLE = ? and AUTHOR_FIRST_NAME = ?";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	               preparedStmt.setString(1, bookTitle);
	               preparedStmt.setString(2, fName);
	           }
	           
	           else if (!bookTitle.isEmpty() && !fName.isEmpty() && !lName.isEmpty() && rating.isEmpty()) {
	        	   String bookSelectSQL ="SELECT * FROM Books WHERE BOOK_TITLE = ? and AUTHOR_FIRST_NAME = ? "
	        	   		+ "and AUTHOR_LAST_NAME = ?";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	               preparedStmt.setString(1, bookTitle);
	               preparedStmt.setString(2, fName);
	               preparedStmt.setString(3, lName);
	           }
	           
	           else if (bookTitle.isEmpty() && !fName.isEmpty() && !lName.isEmpty() && rating.isEmpty()) {
	        	   String bookSelectSQL ="SELECT * FROM Books WHERE AUTHOR_FIRST_NAME = ? "
	        	   		+ "and AUTHOR_LAST_NAME = ?";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	               preparedStmt.setString(1, fName);
	               preparedStmt.setString(2, lName);
	           }
	           
	           else if (bookTitle.isEmpty() && fName.isEmpty() && !lName.isEmpty() && rating.isEmpty()) {
	        	   String bookSelectSQL ="SELECT * FROM Books WHERE AUTHOR_LAST_NAME = ?";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	               preparedStmt.setString(1, lName);
	               
	           }
	           
	           else if (bookTitle.isEmpty() && !fName.isEmpty() && lName.isEmpty() && rating.isEmpty()) {
	        	   String bookSelectSQL ="SELECT * FROM Books WHERE AUTHOR_FIRST_NAME = ?";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	               preparedStmt.setString(1, fName);
	               
	           }
	           else if (bookTitle.isEmpty() && fName.isEmpty() && lName.isEmpty() && !rating.isEmpty()) {
	        	   String bookSelectSQL ="SELECT * FROM Books WHERE RATING = ?";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	               preparedStmt.setDouble(1, Double.valueOf(rating));
	               
	           }
	           
	           
	           else {
	               String bookSelectSQL ="SELECT * FROM Books";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	            
	            }
	           
	           ResultSet rs = preparedStmt.executeQuery();
	           PrintWriter out = response.getWriter();
	   	        String title = "Result from your search!";
	   	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	   	       response.setContentType("text/html");
	   	       out.println(docType + //
	   	              "<html>\n" + //
	   	              "<head><title>" + title + "</title></head>\n" + //
	   	              "<body bgcolor=\"#f0f0f0\">\n" + //
	   	              "<h2 align=\"center\">" + title + "</h2>\n");
	           while (rs.next()) {
	        	   String bookName = rs.getString("BOOK_TITLE").trim();
	        	   String authorFirstName = rs.getString("AUTHOR_FIRST_NAME").trim();
	        	   String authorLastName = rs.getString("AUTHOR_LAST_NAME").trim();
	        	   String isbn = rs.getString("ISBN").trim();
	        	   String newName = bookName.replace(" ", "_");
	        	   //String bookAuthor = rs.getString("BOOK_AUTHOR").trim();
	        	   
	        	   out.println(docType + //
	 	   	              "<ul>\n" + //
	        			   
						" <li><a href=/Bookstore-Project/details/" + newName + 
	        			   

	 	   	              " id=\"book\" <b>Book Name</b> </a>: " + bookName + "\n" + //
	 	   	              "  <li><b>Author Last Name</b>: " + authorFirstName + "\n" + //
	 	   	          	"  <li><b>Author First Name</b>: " + authorLastName + "\n" + //
	 	   	        "  <li><b>ISBN</b>: " + isbn + "\n" + //

	 	   	             
	 	   	              "</ul>\n");   
	        	   
	        	   //out.println("<a href=" + bookURL + ">View book!</a> <br>");
	 	           }
	 	           out.println("<a href=/Bookstore-Project/index.html id=\"back\" >Back to HomePage!</a> <br>");
	 		   	   out.println("</body></html>");
	 	           
	 	           
	 	           
	 	           connection.close();
	           
		 }catch (Exception e) {
	           e.printStackTrace();
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
