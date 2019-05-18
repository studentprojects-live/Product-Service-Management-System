import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class viewProductStock extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String did,prid;
	webman.ProductStock PRS=new webman.ProductStock();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		did=req.getParameter("did");
    	prid=req.getParameter("prid");
			if (!did.equals(""))
			{
			v=PRS.ShowProductStock(did,prid);
  	pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE>");
	pw.println("</head><P align=center><FONT color=deepskyblue size=4><STRONG>PRODUCTSTOCK INFORMATION</STRONG></FONT></P> ");
	pw.println("<body><br><br>");
	pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
	pw.println("<TR><TD>DealerID</TD><TD>"+(String)v.elementAt(0)+"</TD></TR>");
	pw.println("<TR><TD>ProductID</TD><TD>"+(String)v.elementAt(1)+"</TD></TR><TR><TD>Quantity</TD>");
	pw.println("<TD>"+(String)v.elementAt(2)+"</TD></TR><TR><TD>ReOrderLevel</TD><TD>");
    pw.println((String)v.elementAt(3));
    pw.println("</TD></TR>");
	pw.println("<TR><TD ALIGN=CENTER><A HREF='./getproductstock'>continue</a></table></center></body></html>");
	pw.flush();
	pw.close();
			}
			}
}