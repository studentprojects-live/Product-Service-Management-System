import webman.*;
import java.io.*;

import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class deleteProductStock extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	Vector v=new Vector();
	Vector v1=new Vector();
	int i;
	String id;
	webman.Dealer d=new webman.Dealer();
	webman.Product pr=new webman.Product();
	webman.ProductStock PRS=new webman.ProductStock();
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(false);
		PrintWriter out=res.getWriter();
		out.println("WELCOME TO THE         "+hs.getAttribute("uid"));
		try
		{
 		    PrintWriter pw=res.getWriter();
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></head>");
			pw.println("<body><br><br><br><form name=DeletePrStock method=post action='./showproductstock1'>");
            v1=pr.allProducts();
			v=d.allDealers();
		    pw.println("<table align='center' border=0> <tr><td>");
			pw.println("Select Dealer ID</td><td><SELECT id=select1 name=did style='HEIGHT: 22px; LEFT: 74px; TOP: 222px; WIDTH: 155px'>"); 
			pw.println("<OPTION selected value=''></OPTION>");
			for(i=0;i<v.size();i++)
				pw.println("<OPTION value="+(String)v.elementAt(i)+">"+(String)v.elementAt(i)+"</OPTION>");
     		pw.println("</SELECT></td></tr>");
			pw.println("<tr><td>Select Product ID</td><td><SELECT id=select2 name=prid style='HEIGHT: 22px; LEFT: 74px; TOP: 222px; WIDTH: 155px'>"); 
			pw.println("<OPTION selected value=''></OPTION>");
			for(i=0;i<v1.size();i++)
				pw.println("<OPTION value="+(String)v1.elementAt(i)+">"+(String)v1.elementAt(i)+"</OPTION>");
     		pw.println("</SELECT></td></tr><tr><td></td><td><input type='submit' name='submit' value='Submit'></td></tr></table></form></body></html>");
			pw.flush();
			pw.close();

			}catch(Exception e){}
	}

}