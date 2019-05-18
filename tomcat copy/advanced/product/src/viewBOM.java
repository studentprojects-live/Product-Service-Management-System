import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class viewBOM extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String did,prid;
	webman.BillOfMaterials B=new webman.BillOfMaterials();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		did=req.getParameter("prid");
    	prid=req.getParameter("pid");
			if (!did.equals(""))
			{
			v=B.ShowBOM(did,prid);
  	pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE>");
	pw.println("</head><P align=center><FONT color=deepskyblue size=4><STRONG>BILL OF MATERIAL INFORMATION</STRONG></FONT></P> ");
	pw.println("<body><br><br>");
	pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
	pw.println("<TR><TD>ProcutID</TD><TD>"+(String)v.elementAt(0)+"</TD></TR>");
	pw.println("<TR><TD>PartID</TD><TD>"+(String)v.elementAt(1)+"</TD></TR><TR><TD>NO.of Part Required</TD>");
	pw.println("<TD>"+(String)v.elementAt(2)+"</TD></TR>");
	pw.println("<TR><TD ALIGN=CENTER><A HREF='./getbom'>continue</a></table></center></body></html>");
	pw.flush();
	pw.close();
			}
			}
}