import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/deldata")
public class Deleteserv extends HttpServlet
{
	private final static String query="delete from user where id= ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{ PrintWriter pw= resp.getWriter();
	
	  resp.setContentType("text/html");
	  
	  pw.println("<link rel='stylesheet' href='css/bootstrap.css'</link>");
	  //getting the id from the user to delete it's data
	  int id=Integer.parseInt(req.getParameter("id"));
	  
	  //loading a driver
	  try
	  {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	  try
       (Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanagement","root","Root");
			  PreparedStatement ps=con.prepareStatement(query);)
	  {
		  ps.setInt(1, id);
		  
		  // non select query so to show update on browser write using PrintWriter
		  pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		  int cou=ps.executeUpdate();
		  if(cou==1)
		  {
			  pw.println("<h2 class='bg-danger text-light text-center'>Record Deleted ");
		  }
		  else
		  { pw.println("some error occur");
			  
		  } 
	  }
	  catch(SQLException se)
	  {
		  pw.println(se.getMessage());
		  se.printStackTrace();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  pw.println("<h2><a href='home.html'><button class='btn btn-outline-success'>Home</button></a></h2>");
	  pw.println("&nbsp;");
	  pw.println("<h2><a href='showme'><button class='btn btn-outline-success'>Show user</button></a></h2>");
	  pw.println("</div>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
