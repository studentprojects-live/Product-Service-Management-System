import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class addPrds extends HttpServlet
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
		 PrintWriter pw=res.getWriter();
		 hs=req.getSession(false);
		try
		{
			
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></head>");
			pw.println("<body><P align=center><FONT color=deepskyblue size=4><STRONG>ADD PRODUCT STOCK DETAILS</STRONG></FONT></P><form name=additionPrds method=post action='./addproductstock')>");
            v1=pr.allProducts();
			v=d.allDealers();
			pw.println(hs.getAttribute("uid"));
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
     		pw.println("</SELECT></td></tr><TR><TD>Quantity&nbsp;&nbsp;</TD><TD><INPUT id=text2 name=qty></TD></TR><TR><TD>ReOrder Level&nbsp;</TD><TD><INPUT id=text4 name=rol></TD></TR>");
       		pw.println("<tr><td></td><td><input type='submit' name='submit' value='Submit'></td></tr></table></form></body></html>");
			pw.flush();
     		pw.close();
			}catch(Exception e){}
	}

}
