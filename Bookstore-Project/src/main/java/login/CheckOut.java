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

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		Connection connection = null;
		try {
			DBConnectionDjossou.getDBConnection();
	           connection = DBConnectionDjossou.connection;
	           PreparedStatement preparedStmt = null;
	           
	           		System.out.println("the username is" + username);
	            	String bookSelectSQL ="DELETE FROM USER_BOOKS WHERE USERNAME='" + username + "'";
		            preparedStmt = connection.prepareStatement(bookSelectSQL);
		            
		            preparedStmt.executeUpdate();
	           
	          
	           PrintWriter out = response.getWriter();
	   	        String title = "Checked out!";
	   	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	   	       response.setContentType("text/html");
	   	       out.println(docType + //
	   	              "<html>\n" + //
	   	              "<head>\r\n"
	   	              + "    <title>CheckedOut!</title>\r\n"
	   	              + "    <style>\r\n"
	   	              + "      .searchbox {\r\n"
	   	              + "        float: left;\r\n"
	   	              + "      }\r\n"
	   	              + "      input[type=\"text\"] {\r\n"
	   	              + "        padding: 6px;\r\n"
	   	              + "        margin-top: 8px;\r\n"
	   	              + "        font-size: 17px;\r\n"
	   	              + "        border: none;\r\n"
	   	              + "      }\r\n"
	   	              + "      .searchbox button {\r\n"
	   	              + "        padding: 8px;\r\n"
	   	              + "        margin-top: 10px;\r\n"
	   	              + "        margin-left: 10px;\r\n"
	   	              + "        font-size: 20px;\r\n"
	   	              + "        border: none;\r\n"
	   	              + "        cursor: pointer;\r\n"
	   	              + "      }\r\n"
	   	              + "\r\n"
	   	              + "      .searchbox button:hover {\r\n"
	   	              + "        background: blue;\r\n"
	   	              + "      }\r\n"
	   	              + "    </style>\r\n"
	   	              + "    <body style=\"background-color: tan\"></body>\r\n"
	   	              + "    <link rel=\"stylesheet\" href=\"styles/navbar.css\" />\r\n"
	   	              + "    <link rel=\"stylesheet\" href=\"styles/search.css\" />\r\n"
	   	              + "    <script src=\"https://use.fontawesome.com/d721eef411.js\"></script>\r\n"
	   	              + "  </head>\r\n"
	   	              + "  <body>\r\n"
	   	              + "    <nav>\r\n"
	   	              + "      <div class=\"heading\">\r\n"
	   	              + "        <h4>GAYZ BookStore</h4>\r\n"
	   	              + "      </div>\r\n"
	   	              + "      <ul class=\"nav-links\">\r\n"
	   	              + "        <li><a class=\"active\" href=\"index.html\">Home</a></li>\r\n"
	   	              + "        <li><a class=\"active\" href=\"priceTool.html\">PCT</a></li>\r\n"
	   	              + "        <li><a class=\"active\" href=\"search.html\">Search</a></li>\r\n"
	   	              + "        <li>\r\n"
	   	              + "          <a class=\"active\" href=\"cart.html\"\r\n"
	   	              + "            ><i class=\"fa fa-shopping-cart\" aria-hidden=\"true\"></i\r\n"
	   	              + "          ></a>\r\n"
	   	              + "        </li>\r\n"
	   	              + "      </ul>\r\n"
	   	              + "    </nav>");
	           
	        	   //String bookAuthor = rs.getString("BOOK_AUTHOR").trim();
	        	   
	        	   out.println(docType + //
	 	   	             "<h1>You've checked out!</h1>" + "<p>Thank you for your business! We were happy to serve you!</br>");
	        	   
	        	   //out.println("<a href=" + bookURL + ">View book!</a> <br>");
	 	           
	           	  
	        	   out.println("<a href=/Bookstore-Project/index.html><button>Back to homepage!</button></a><br>");
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
