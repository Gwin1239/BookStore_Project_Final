package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cart")
public class LoadCart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoadCart() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		Connection connection = null;
		try {
			DBConnectionDjossou.getDBConnection();
			connection = DBConnectionDjossou.connection;
			PreparedStatement preparedStmt = null;

			if (username.isEmpty()) {
				
				PrintWriter out = response.getWriter();
	   	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		   	       response.setContentType("text/html");
        	   
        	   out.println(docType + //
	 	   	              "<p> Error in Username </p>" );   
	        	   
	        	   //out.println("<a href=" + bookURL + ">View book!</a> <br>");
	 	           
	 	           out.println("<a href=/Bookstore-Project/index.html id=\"back\" >Back to HomePage!</a> <br>");
	 		   	   out.println("</body></html>");
				
				System.out.println("Inputted book DNE!");
				String bookSelectSQL = "SELECT * FROM USER_BOOKS";
				preparedStmt = connection.prepareStatement(bookSelectSQL);

			} else {
				String bookSelectSQL = "SELECT * FROM USER_BOOKS WHERE USERNAME='" + username + "'";
				preparedStmt = connection.prepareStatement(bookSelectSQL);

				Double cartTotal = 0.0;
				ResultSet rs = preparedStmt.executeQuery();
				PrintWriter out = response.getWriter();
				String title = "Here is Your Cart!";
				String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
				response.setContentType("text/html");
				out.println(docType + //
						"<html>\n" + //
						"<head><title>" + title + "</title></head>\n" + //
						"<body bgcolor=\"#f0f0f0\">\n" + //
						"<h2 align=\"center\">" + title + "</h2>\n");
				while (rs.next()) {
					String bookName = rs.getString("BOOK_TITLE").trim();
					Double price = rs.getDouble("PRICE");
					// String bookAuthor = rs.getString("BOOK_AUTHOR").trim();
					cartTotal += price;

					out.println(docType + //
							"<ul>\n" + //

							"  <li><b>Book Name</b>: " + bookName + "\n" + //
							"  <li><b>Book Price</b>: " + price + "\n" + //

							"</ul>\n");

					// out.println("<a href=" + bookURL + ">View book!</a> <br>");
				}
				out.println("<p>Cart Total: $" + cartTotal + "</p>");
				
				out.println("    <div class=\"searchbox\" style=\"margin-top: 15px; margin-right: 200px\">\n"
					+ "      <form action=CheckOut method=\"POST\">\n"
					+ "        Username: <input type=\"text\" name=\"username\" value=" + username + "> <br />\n"
					+ "        First and Last Name: <input type=\"text\" name=\"title\" > <br />\n"
					+ "\n"
					+ "        Credit Card Number: <input type=\"text\" name=\"card\"> <br />\n"
					+ "\n"
					+ "        Pin: <input type=\"text\" name=\"pin\" /> <br />\n"
					+ "\n"
					+ "        <br />\n"
					+ "\n"
					+ "        <input type=\"submit\" value=\"Check Out\" onclick=\"myFunction()\" />\n"
					+ "      </form>\n"
					+ "    </div>\n");
				
		        	   

				out.println("<br>");
				out.println("<a href=/Bookstore-Project/index.html id=\"back\" >Back to HomePage!</a> <br>");
				out.println("</body></html>");
			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
