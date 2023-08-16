import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/showme")
public class ShowUserServlet extends HttpServlet
{
	private final static String query="select id,name,email,mobile,dob,city,gender from user";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{ PrintWriter pw= resp.getWriter();
	
	  resp.setContentType("text/html");
	  //bootstrap linked 
	  pw.println("<link rel='stylesheet' href='css/bootstrap.css'</link>");
	
	  
	  //loading a driver
	  try
	  {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  //connection loading
	  try
       (Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanagement","root","Root");
			  PreparedStatement ps=con.prepareStatement(query);)
	  {
		  //setting a database to show on browser
		ResultSet rs=ps.executeQuery();
        pw.println("<div style='margin:auto;width:900px;margin-top:100px;'>");
		pw.println("<table class ='table table-hover table-striped'>");
		pw.println("<tr>");
		pw.println("<th>ID</th>");
		pw.println("<th>Name</th>");
		pw.println("<th>Email</th>");
		pw.println("<th>Mobile</th>");
		pw.println("<th>Dob</th>");
		pw.println("<th>City</th>");
		pw.println("<th>Gender</th>");
		pw.println("<th>Edit</th>");
		pw.println("<th>Delete</th>");
		pw.println("</tr>");
		while(rs.next())
		{
		 pw.println("<tr>");
		 pw.println("<td>"+rs.getInt(1)+"</td>");
		 pw.println("<td>"+rs.getString(2)+"</td>");
		 pw.println("<td>"+rs.getString(3)+"</td>");
		 pw.println("<td>"+rs.getString(4)+"</td>");
		 pw.println("<td>"+rs.getString(5)+"</td>");
		 pw.println("<td>"+rs.getString(6)+"</td>");
		 pw.println("<td>"+rs.getString(7)+"</td>");
		 //now create two button for delete and edit data\
		 pw.println("<td><a href='edurl?id="+rs.getInt(1)+" '>Edit</a></td>");
		 pw.println("<td><a href='deldata?id="+rs.getInt(1)+" '>Delete</a></td>");
			pw.println("</tr>");
		}
	  pw.println("</table>");
		  
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
