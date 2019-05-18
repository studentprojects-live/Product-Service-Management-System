import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class showProductStock1 extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String did;
	String prid;
	webman.ProductStock PRS=new webman.ProductStock();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		try{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		did=req.getParameter("did");
		prid=req.getParameter("prid");
		
if (!did.equals(""))
{
v=PRS.ShowProductStock(did,prid);
pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></script></head><P align=center><FONT color=deepskyblue size=4><STRONG>DELETE&nbsp;PRODUCTSTOCK </STRONG></FONT></P> ");
pw.println("<body><form name=delProductStock method=post action='./delproductstock'>");
pw.println("<center><TABLE align=center style='WIDTH: 324px; HEIGHT: 157px' cellSpacing=1 cellPadding=1 width=324 border=0>");
pw.println("<TR><TD>Dealer ID&nbsp;&nbsp;</TD><TD><INPUT id=text1 name=did value="+(String)v.elementAt(0)+"></TD></TR><TR><TD>Product ID&nbsp;</TD><TD><INPUT id=text3 name=pid value="+(String)v.elementAt(1)+"></TD></TR>");
pw.println("<TR><TD>Quantity&nbsp;&nbsp;</TD><TD><INPUT id=text2 name=qty value="+(String)v.elementAt(2)+"></TD></TR><TR><TD>ReOrder Level&nbsp;</TD><TD>");
pw.println("<INPUT id=text4 name=rol value="+(String)v.elementAt(3)+"><INPUT id=submit1 style='LEFT: 151px; TOP: 318px' type=submit value=Delete name=submit1></TD></TR>");
pw.println("</table></center></form></body></html>");
pw.flush();
pw.close();
}
}catch(Exception e){}
}
			
            
}