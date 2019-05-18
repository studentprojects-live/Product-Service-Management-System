import webman.*;
import java.io.*;

import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class getUser extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	Vector v=new Vector();
	int i;
	String id;
	webman.User U=new webman.User();
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(false);
		PrintWriter out=res.getWriter();
		out.println("WELCOME  TO    "+hs.getAttribute("uid"));
		try
		{
 		    PrintWriter pw=res.getWriter();
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></head>");
			pw.println("<body><br><br><br><form name=viewuser method=post action='./viewuser')");
                        v=U.allUsers();
		        pw.println("<table align='center' border=0> <tr><td>");
			pw.println("Select User Name To Display Details</td><td><SELECT id=select1 name=uid style='HEIGHT: 22px; LEFT: 74px; TOP: 222px; WIDTH: 155px'>");
			pw.println("<OPTION selected value=''></OPTION>");
			for(i=0;i<v.size();i++)
				pw.println("<OPTION value="+(String)v.elementAt(i)+">"+(String)v.elementAt(i)+"</OPTION>");
     		        pw.println("</SELECT></td></tr><tr><td></td><td><input type='submit' name='submit' value='Submit'></td></tr></table></form></body></html>");
			pw.flush();
			pw.close();

			}catch(Exception e){}
	}

}