import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class showProduct extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String prid;
	webman.Product PR=new webman.Product();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		try{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		prid=req.getParameter("prid");
		
if (!prid.equals(""))
{
v=PR.ShowProduct(prid);
pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE><script language=javascript>function set() {");
pw.println("document.updateProduct.select1.value='"+(String)v.elementAt(4)+"'} </script></head><P align=center><FONT color=deepskyblue size=4><STRONG>MODIFY&nbsp;PRODUCT </STRONG></FONT></P> ");
pw.println("<body onLoad=set()><form name=updateProduct method=post action='./updateproduct'>");
pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
pw.println("<TR><TD>Product Id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD><TD><INPUT id=text1 name=prid value="+(String)v.elementAt(0)+"></TD></TR>");
pw.println("<TR><TD>ProductName&nbsp;</TD><TD><INPUT id=text2 name=prname value='"+(String)v.elementAt(1)+"'></TD></TR><TR><TD>ProductDescription&nbsp;</TD>");
pw.println("<TD><INPUT id=text3 name=prdesc value='"+(String)v.elementAt(2)+"'></TD></TR><TR><TD>Price</TD><TD><INPUT id=text4 name=price value="+(String)v.elementAt(3)+">");
pw.println("</TD></TR><TR><TD><P>Staus</P></TD><td><SELECT id=select1 name=status style='HEIGHT: 22px; LEFT: 1px; TOP: 1px; WIDTH: 136px'> <OPTION ");
pw.println("selected value=''></OPTION><OPTION value=Active>Active</OPTION><OPTION value=Inactive>Inactive</OPTION></SELECT><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value=Update></TD></TR>");
pw.println("</table></center></form></body></html>");
pw.flush();
pw.close();
}
}catch(Exception e){}
}
			
            
}