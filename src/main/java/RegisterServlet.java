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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
	//writing query to update in database 
	private final static String query="insert into user(name,email,mobile,dob,city,gender) values(?,?,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{ PrintWriter pw= resp.getWriter();
	
	  resp.setContentType("text/html");
	  
	  pw.println("<link rel='stylesheet' href='css/bootstrap.css'</link>");
	  //getting the values from the user
	  String name=req.getParameter("username");
	  String email=req.getParameter("email");
	  String mobile=req.getParameter("mobile");
	  String dob=req.getParameter("dob");
	  String city=req.getParameter("city");
	  String gender=req.getParameter("gender");
	  
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
		  ps.setString(1, name);
		  ps.setString(2, email);
		  ps.setString(3, mobile);
		  ps.setString(4, dob);
		  ps.setString(5, city);
		  ps.setString(6, gender);
		  // non select query so to show update on browser write using PrintWriter
		  pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		  int cou=ps.executeUpdate();
		  if(cou==1)
		  {
			  pw.println("<h2 class='bg-danger text-light text-center'>Record Is Registerd");
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
	  pw.println("</div>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	

}
