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

@WebServlet(urlPatterns={"/details", "/details/*"})
public class BookDetails extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	   public BookDetails() {
	      super();
	   }
	   
	      

	   
	   
	   
	   
	   ///////////////
	   
	   /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
			//String bookTitle = request.getPathInfo().replace("/", "");
			
			String bookTitle = request.getPathInfo().replace("/", "").replace("_", " ");

			
			String selectSql = " SELECT * FROM Books WHERE BOOK_TITLE LIKE ?";
			
			
		      
		     System.out.println(bookTitle);
			
			//String username = request.getParameter("username");
			Connection connection = null;
			try {
				DBConnectionDjossou.getDBConnection();
		           connection = DBConnectionDjossou.connection;
		           PreparedStatement preparedStmt = null;
		           
		           if (bookTitle.isEmpty()) {
		        	   
		        	   PrintWriter out = response.getWriter();
			   	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
				   	       response.setContentType("text/html");
		        	   
		        	   out.println(docType + //
			 	   	              "<p> Error in Book Details </p>" );   
			        	   
			        	   //out.println("<a href=" + bookURL + ">View book!</a> <br>");
			 	           
			 	           out.println("<a href=/Bookstore-Project/index.html id=\"back\" >Back to HomePage!</a> <br>");
			 		   	   out.println("</body></html>");

		        	   
		              System.out.println("Inputted book DNE!");
		              String bookSelectSQL ="SELECT * FROM Books";
		               preparedStmt = connection.prepareStatement(bookSelectSQL);
		              
		              
		            } else {
		            	String bookSelectSQL ="SELECT * FROM Books WHERE BOOK_TITLE ='" + bookTitle + "'";
		               preparedStmt = connection.prepareStatement(bookSelectSQL);
		               //String like = "%" + bookTitle + "%";
		               //preparedStmt.setString(1, like);
		               
		            
		            }
		           
		           ResultSet rs = preparedStmt.executeQuery();
		           PrintWriter out = response.getWriter();
		   	        String title = "Display Book Details";
		   	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		   	       response.setContentType("text/html");
		   	       
		   	       /*
		   	       out.println(docType + //
		   	              "<html>\n" + //
		   	              "<head><title>" + title + "</title></head>\n" + //
		   	              "<body bgcolor=\"#f0f0f0\">\n" + //
		   	              "<h2 align=\"center\">" + title + "</h2>\n"); */
		   	       
		   	       
		   	       
		   	       
		   	       
		   	       
		   	       //code for queryresult
		           while (rs.next()) {
		        	   String bookName = rs.getString("BOOK_TITLE").trim();
		        	   String fName = rs.getString("AUTHOR_FIRST_NAME").trim();
		        	   String lName = rs.getString("AUTHOR_LAST_NAME").trim();
		        	   String isbn = rs.getString("ISBN").trim();
		        	   Float price = rs.getFloat("PRICE");
		        	   String image = rs.getString("IMAGE_URL").trim();
		        	   String desc = rs.getString("DESCRIPTION").trim();
		        	   int stock =  rs.getInt("STOCK");
		        	   Double rating =  rs.getDouble("RATING");
		        	   //String bookAuthor = rs.getString("BOOK_AUTHOR").trim();
		        	   
		        	   /*
		        	   out.println(docType + //
		 	   	              "<ul>\n" + //

		 	   	              "  <li><b>Book Name</b>: " + bookName + "\n" + //
		 	   	              "  <li><b>First Name</b>: " + fName + "\n" + //
		 	   	              "  <li><b>Last Name</b>: " + lName + "\n" + //
		 	   	          "  <li><b>ISBN</b>: " + isbn + "\n" + //
		 	   	      "  <li><b>Price</b>: " + price + "\n" + //

		 	   	             
		 	   	              "</ul>\n");   */
		        	   if (stock == 1) {
		        		   out.println(createHTMLLowStock(bookName, fName, lName, price, isbn, image, desc, rating));
		        	   }
		        	   else if (stock <= 0) {
		        		   
		        	   }
		        	   else {
		        		   out.println(createHTML(bookName, fName, lName, price, isbn, image, desc, rating));
		        	   }
		        	   //out.println(createHTML(bookName, fName, lName, price, isbn, image, desc));
		        	   
		        	   //out.println("<a href=" + bookURL + ">View book!</a> <br>");
		 	           }
		 	           //out.println("<a href=/Bookstore-Project/index.html>Back to HomePage!</a> <br>");
		 		   	   //out.println("</body></html>");
		 	           
		 	           
		 	           
		 	           connection.close();
		           
			 }catch (Exception e) {
		           e.printStackTrace();
		      }
			
		}
			
		
		public String createHTML(String bookName, String fName, String lName, Float price, String isbn, String image, String desc, Double rating) {
			String html = "<!DOCTYPE html>\n"
					+ "<html>\n"
					+ "  <head>\n"
					+ "    <title>Search</title>\n"
					+ "    <style>\n"
					+ "      .searchbox {\n"
					+ "        float: left;\n"
					+ "      }\n"
					+ "      input[type=\"text\"] {\n"
					+ "        padding: 6px;\n"
					+ "        margin-top: 8px;\n"
					+ "        font-size: 17px;\n"
					+ "        border: none;\n"
					+ "      }\n"
					+ "      .searchbox button {\n"
					+ "        padding: 8px;\n"
					+ "        margin-top: 10px;\n"
					+ "        margin-left: 10px;\n"
					+ "        font-size: 20px;\n"
					+ "        border: none;\n"
					+ "        cursor: pointer;\n"
					+ "      }\n"
					+ "\n"
					+ "      .searchbox button:hover {\n"
					+ "        background: blue;\n"
					+ "      }\n"
					+ "    </style>\n"
					+ "    <body style=\"background-color: tan\"></body>\n"
					+ "    <link rel=\"stylesheet\" href=/Bookstore-Project/styles/navbar.css>\n"
					+ "    <link rel=\"stylesheet\" href=/Bookstore-Project/styles/search.css>\n"
					+ "    <link rel=\"stylesheet\" href=\"display.css\" />\n"
					+ "    <script src=\"https://use.fontawesome.com/d721eef411.js\"></script>\n"
					+ "  </head>\n"
					+ "  <body>\n"
					+ "    <nav>\n"
					+ "      <div class=\"heading\">\n"
					+ "        <h4>GAYZ BookStore</h4>\n"
					+ "      </div>\n"
					+ "      <ul class=\"nav-links\">\n"
					+ "        <li><a class=\"active\" href=/Bookstore-Project/index.html id=\"home\" >Home</a></li>\n"
					+ "        <li><a class=\"active\" href=/Bookstore-Project/index.html  id=\"price\">PCT</a></li>\n"
					+ "        <li><a class=\"active\" href=/Bookstore-Project/search.html id=\"search\" >Search</a></li>\n"
					+ "        <li>\n"
					+ "          <a class=\"active\" href=/Bookstore-Project/cart.html id=\"cart\" \n"
					+ "            ><i class=\"fa fa-shopping-cart\" aria-hidden=\"true\"></i\n"
					+ "          ></a>\n"
					+ "        </li>\n"
					+ "      </ul>\n"
					+ "    </nav>\n"
					+ "\n"
					+ "    <h1>Display Book Details</h1>\n"
					+ "\n"
					+ "    <div style=\"text-align: center\">\n"
					+ "      <img\n"
					+ "        src=" + image + "\n"
					+ "        alt=\"Sky\"\n"
					+ "        style=\"width: 400px; height: 400px; margin-top: 10px\"\n"
					+ "      />\n"
					+ "    </div>\n"
					+ "\n"
					+ "    <div class=\"bookDetails\" style=\"text-align: center\">\n"
					+ "      <p>Book Name: " + bookName + "</p>\n"
					+ "      <p>Author First Name: " + fName + "</p>\n"
					+ "      <p>Author Last Name: " + lName+ "</p>\n"
					+ "      <p>ISBN: " + isbn + "</p>\n"
					+ "      <p>Price: $" + price + "</p>\n"
					+ "      <p>Rating: " + rating + " stars </p>\n"
					+ "      <p>Description: " + desc + "</p>\n"
					+ "    </div>\n"
					+ "\n"
					+ "    <div class=\"searchbox\" style=\"margin-top: 15px; margin-right: 200px\">\n"
					+ "      <form action=/Bookstore-Project//AddToCart method=\"POST\">\n"
					+ "        UserName: <input type=\"text\" name=\"username\" id=\"user\" /> <br />\n"
					+ "        Book Name: <input type=\"text\" name=\"title\" value=" + bookName + "> <br />\n"
					+ "        Price: <input type=\"text\" name=\"price\" value=" + price + "> <br />\n"
					+ "\n"
					+ "        Add to Cart?<br />\n"
					+ "\n"
					+ "        <br />\n"
					+ "\n"
					+ "        <input type=\"submit\" value=\"Add to Cart\" id=\"submit\" />\n"
					+ "      </form>\n"
					+ "    </div>\n"
					+ "  </body>\n"
					+ "</html>";
			
			return html;
		}
		
		public String createHTMLLowStock(String bookName, String fName, String lName, Float price, String isbn, String image, String desc, Double rating) {
			String html = "<!DOCTYPE html>\n"
					+ "<html>\n"
					+ "  <head>\n"
					+ "    <title>Search</title>\n"
					+ "    <style>\n"
					+ "      .searchbox {\n"
					+ "        float: left;\n"
					+ "      }\n"
					+ "      input[type=\"text\"] {\n"
					+ "        padding: 6px;\n"
					+ "        margin-top: 8px;\n"
					+ "        font-size: 17px;\n"
					+ "        border: none;\n"
					+ "      }\n"
					+ "      .searchbox button {\n"
					+ "        padding: 8px;\n"
					+ "        margin-top: 10px;\n"
					+ "        margin-left: 10px;\n"
					+ "        font-size: 20px;\n"
					+ "        border: none;\n"
					+ "        cursor: pointer;\n"
					+ "      }\n"
					+ "\n"
					+ "      .searchbox button:hover {\n"
					+ "        background: blue;\n"
					+ "      }\n"
					+ "    </style>\n"
					+ "    <body style=\"background-color: tan\"></body>\n"
					+ "    <link rel=\"stylesheet\" href=/Bookstore-Project/styles/navbar.css>\n"
					+ "    <link rel=\"stylesheet\" href=/Bookstore-Project/styles/search.css>\n"
					+ "    <link rel=\"stylesheet\" href=\"display.css\" />\n"
					+ "    <script src=\"https://use.fontawesome.com/d721eef411.js\"></script>\n"
					+ "  </head>\n"
					+ "  <body>\n"
					+ "    <nav>\n"
					+ "      <div class=\"heading\">\n"
					+ "        <h4>GAYZ BookStore</h4>\n"
					+ "      </div>\n"
					+ "      <ul class=\"nav-links\">\n"
					+ "        <li><a class=\"active\" href=/Bookstore-Project/index.html id=\"home\" >Home</a></li>\n"
					+ "        <li><a class=\"active\" href=/Bookstore-Project/index.html  id=\"price\">PCT</a></li>\n"
					+ "        <li><a class=\"active\" href=/Bookstore-Project/search.html id=\"search\" >Search</a></li>\n"
					+ "        <li>\n"
					+ "          <a class=\"active\" href=/Bookstore-Project/cart.html id=\"cart\" \n"
					+ "            ><i class=\"fa fa-shopping-cart\" aria-hidden=\"true\"></i\n"
					+ "          ></a>\n"
					+ "        </li>\n"
					+ "      </ul>\n"
					+ "    </nav>\n"
					+ "\n"
					+ "    <h1>Display Book Details</h1>\n"
					+ "\n"
					+ "    <div style=\"text-align: center\">\n"
					+ "      <img\n"
					+ "        src=" + image + "\n"
					+ "        alt=\"Sky\"\n"
					+ "        style=\"width: 400px; height: 400px; margin-top: 10px\"\n"
					+ "      />\n"
					+ "    </div>\n"
					+ "\n"
					+ "    <div class=\"bookDetails\" style=\"text-align: center\">\n"
					+ "      <p>Book Name: " + bookName + "</p>\n"
					+ "      <p>Author First Name: " + fName + "</p>\n"
					+ "      <p>Author Last Name: " + lName+ "</p>\n"
					+ "      <p>ISBN: " + isbn + "</p>\n"
					+ "      <p>Price: $" + price + "</p>\n"
					+ "      <p>Rating: " + rating + " stars </p>\n"
					+ "      <p>Description: " + desc + "</p>\n"
					+ "    </div>\n"
					+ "\n"
					+ "    <div class=\"searchbox\" style=\"margin-top: 15px; margin-right: 200px\">\n"
					+ "      <form action=/Bookstore-Project//AddToCart method=\"POST\">\n"
					+ "        UserName: <input type=\"text\" name=\"username\" id=\"user\" /> <br />\n"
					+ "        Book Name: <input type=\"text\" name=\"title\" value=" + bookName + "> <br />\n"
					+ "        Price: <input type=\"text\" name=\"price\" value=" + price + "> <br />\n"
					+ "\n"
					+ "        Add to Cart?<br />\n"
					+ "\n"
					+ "        <br />\n"
					+ "\n"
					+ "        <input type=\"submit\" value=\"Add to Cart\" id=\"submit\" onclick=\"clicked();\" />\n"
					+ "      </form>\n"
           			+ "\n"
           			+ "<script>\n"
           			+ "function clicked() {\n"
           			+ "  alert(\"Low Stock!\");\n"
           			+ "}\n"
           			+ "</script>"
					+ "    </div>\n"
					+ "  </body>\n"
					+ "</html>";
			
			return html;
		}
	   
	   
	   

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doGet(request, response);
	   }
	

}
