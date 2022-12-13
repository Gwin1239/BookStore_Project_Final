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

@WebServlet(urlPatterns={"/ChangePrice", "/ChangePrice/*"})
public class ChangePrice extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	   public ChangePrice() {
	      super();
	   }
	
	   
	   
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		   
		   String bookTitle = request.getParameter("title");
		   
		   Double oldPrice = Double.valueOf(request.getParameter("oldPrice"));
		   
			Double price = Double.valueOf(request.getParameter("price"));
			
			ResultSet rs = null;
			
			
			Connection connection = null;
			try {
				DBConnectionDjossou.getDBConnection();
		           connection = DBConnectionDjossou.connection;
		           PreparedStatement preparedStmt = null;
		           
		           if (bookTitle.isEmpty()) {
		              //System.out.println("Nothing to Update");
		              //String bookSelectSQL ="SELECT * FROM Books";
		               //preparedStmt = connection.prepareStatement(bookSelectSQL);
		              
		            } 
		           
		           
		           else {
		        	   String bookSelectSQL ="SELECT * FROM Books WHERE BOOK_TITLE ='" + bookTitle + "'";
		               //preparedStmt = connection.prepareStatement(bookSelectSQL);
		            
		            
		           
		           //ResultSet rs = preparedStmt.executeQuery();
		           
		           //ouble priceFromQuery = rs.getDouble("PRICE");
		           
		        	   	if (oldPrice > price) {
		        	   		String updatePrice ="Update Books SET PRICE = " + price + " WHERE BOOK_TITLE = '" + bookTitle + "'";
		        	   
		        	   		preparedStmt = connection.prepareStatement(updatePrice);
		        	   
				           
		        	   		preparedStmt.executeUpdate();
		        	   
		        	   // Select
		        	   
		        	   
		        	   
		        	  
		        	   
		        	   //rs =  preparedStmt.executeQuery();
		           			}	
		        	    preparedStmt = connection.prepareStatement(bookSelectSQL);
			        	rs = preparedStmt.executeQuery();
		           
		           	
		           }
		           
		           
		           PrintWriter out = response.getWriter();
		   	        String title = "Updated Price!";
		   	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		   	       response.setContentType("text/html");
		   	       out.println(docType + //
		   	              "<html>\n" + //
		   	              "<head><title>" + title + "</title></head>\n" + //
		   	              "<body bgcolor=\"#f0f0f0\">\n" + //
		   	              "<h2 align=\"center\">" + title + "</h2>\n");
		           while (rs.next()) {
		        	   String bookName = rs.getString("BOOK_TITLE").trim();
		        	   Double price1 = rs.getDouble("PRICE");
		        	   //String newName = bookName.replace(" ", "_");
		        	   //String bookAuthor = rs.getString("BOOK_AUTHOR").trim();
		        	   
		        	   out.println(docType + //
		 	   	              "<ul>\n" + //
		        			   
		 	   	          "  <li><b>Book Name</b>: " + bookName + "\n" + //
		 	   	              "  <li><b>New Price</b>: " + price1 + "\n" + //

		 	   	             
		 	   	              "</ul>\n");   
		        	   
		        	   //out.println("<a href=" + bookURL + ">View book!</a> <br>");
		 	           }
		 	           out.println("<a href=/Bookstore-Project/index.html id=\"home\">Back to HomePage!</a> <br>");
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
