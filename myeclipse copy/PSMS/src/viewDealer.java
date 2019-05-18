import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class viewDealer extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String uid;
	webman.Dealer D=new webman.Dealer();
	Vector v=new Vector();
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		String uid=hs.getValue("uid").toString();
		String cname=hs.getValue("cname").toString();
		String utype=hs.getValue("utype").toString();
		if(!uid.equals(""))
		{
			if (utype.equals("Admin"))
				 uid=req.getParameter("did");
			else if (utype.equals("Dealer"))
				 uid=cname;
System.out.println("UID=" + uid);
			if (!uid.equals(""))
			{
				v=D.getDealer(uid);
System.out.println("Vsiz=" + v.size());
				if(v.size()>0)
				{
				  	pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE>");
					pw.println("</head><P align=center><FONT color=deepskyblue size=4><STRONG>DEALER INFORMATION</STRONG></FONT></P> ");
					pw.println("<body><br><br>");
					pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
					pw.println("<TR><TD>DealerID</TD><TD>"+(String)v.elementAt(0)+"</TD></TR>");
					pw.println("<TR><TD>DealerName</TD><TD>"+(String)v.elementAt(1)+"</TD></TR><TR><TD>DealerAddress</TD>");
					pw.println("<TD>"+(String)v.elementAt(2)+"</TD></TR><TR><TD>Creditlimit</TD><TD>");
				    pw.println((String)v.elementAt(3));
				        pw.println("</TD></TR> <TR> <TD><P>Status</P></TD>");
				        pw.println("<TD>"+(String)v.elementAt(4)+"</TD></TR>");
					if (utype.equals("Admin"))
						pw.println("<TR><TD ALIGN=CENTER><A HREF='./getdealer'>continue</a></table></center></body></html>");
					else if (utype.equals("Dealer"))
					    pw.println("</table></center></body></html>");
					pw.flush();
					pw.close();
				}
			}
		}
	}
}