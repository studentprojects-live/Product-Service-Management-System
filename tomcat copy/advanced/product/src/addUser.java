import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class addUser extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	webman.User U=new webman.User();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
//		HttpSession session = req.getSession(true);
//		Integer ival = (Integer) hs.getValue("sessiontest.counter"); 
		PrintWriter pw=res.getWriter();
			if (U.createUser(req.getParameter("name"),req.getParameter("uid"),req.getParameter("pwd"),req.getParameter("logtype"),req.getParameter("eid"))==true)
			{
            pw.println("<html><head><TITLE>Products And Services Management  Syste</TITLE></head>");
			//pw.println("<body><form name=checkuser action='.htm'>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Products And Services Management  Syste</th></tr>");
			pw.println("<tr><td>User Name :</td><td>"+req.getParameter("uid")+"</td></tr>");
			pw.println("<tr><td>Member of :</td><td>"+req.getParameter("logtype")+"</td></tr>");
			pw.println("<tr><td>Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./AddUser.html' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			}
			}
            
}