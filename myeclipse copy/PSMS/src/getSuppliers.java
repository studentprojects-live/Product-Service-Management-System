import webman.*;
import java.io.*;

import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class getSuppliers extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	Vector v=new Vector();
	int i;
	String id;
	webman.Suppliers S=new webman.Suppliers();
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
			pw.println("<body><br><br><br><form name=viewSuppliers method=post action='./viewsuppliers')");
                        v=S.allSuppliers();
		        pw.println("<table align='center' border=0> <tr><td>");
			pw.println("Select Supplier ID To Display Details</td><td><SELECT id=select1 name=sid style='HEIGHT: 22px; LEFT: 74px; TOP: 222px; WIDTH: 155px'>");
			pw.println("<OPTION selected value=''></OPTION>");
			for(i=0;i<v.size();i++)
				pw.println("<OPTION value="+v.get(i).toString()+">"+v.get(i).toString()+"</OPTION>");
     		        pw.println("</SELECT></td></tr><tr><td></td><td><input type='submit' name='submit' value='Submit'></td></tr></table></form></body></html>");
			pw.flush();
			pw.close();

			}catch(Exception e){}
	}

}