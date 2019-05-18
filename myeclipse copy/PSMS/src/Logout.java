import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet
{
    Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	HttpSession hs=null;
	Object obj=null;

	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		obj=hs.getValue("uid");
		String userid=obj.toString();
		hs.invalidate();
		pw.println("<html>");
		pw.println("<head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></head>");
		pw.println("<body>");
		pw.println("<center>");
		pw.println("<font face='Arial, Helvetica, sans-serif' size='2' color='#0000FF'>  "+userid +"  have been logged off</td></tr></font>");
		pw.println("<a href='Index.htm' target=_top>Click here to return to Main</a></center></body>");
		pw.println("</html>");
	}

}