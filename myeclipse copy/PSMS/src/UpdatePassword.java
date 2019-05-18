import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdatePassword extends HttpServlet
{
 Connection con=null;
 Statement st=null;
 PreparedStatement ps = null;
 PrintWriter pw;
	HttpSession hs=null;  
	String sql=null;
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException
  {
    res.setContentType("text/html");
    pw=res.getWriter();

     try
      {
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       con=DriverManager.getConnection("Jdbc:Odbc:WebMan");

       //st=con.createStatement();
	   hs=req.getSession(true);
  	   String userid=hs.getValue("uid").toString();
	   String pwd=hs.getValue("pwd").toString();
       String oldpwd=req.getParameter("oldpwd");
       String newpwd=req.getParameter("newpwd");
	   String conpwd=req.getParameter("conpwd");
       System.out.println("checking the database");
	   
		   if(pwd.equals(oldpwd))
			 {
			 sql ="update users set password='"+newpwd+"' where LoginName='"+userid+"'"; 
			 ps = con.prepareStatement(sql);
		     int i=ps.executeUpdate();

       pw.println("<html><body>");
	   pw.println("<center><h1><strong><font color='blue'></font></strong></h1>");
       pw.println("<h3>Your Password Has Been Changed Successfully</h3>");
	   pw.println("<a href='ChangePassword.html'></a></center>");
       pw.println("</body></html>");
	   pw.flush();
	   pw.close();
	  }
     }catch(Exception e)
	    {e.printStackTrace();}
    }
}