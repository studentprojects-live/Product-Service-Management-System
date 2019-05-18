import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class viewSuppliers extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String sid;
	webman.Suppliers S=new webman.Suppliers();
	Vector v=new Vector();
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
	  try{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		String uid=hs.getValue("uid").toString();
		String cname=hs.getValue("cname").toString();
		String utype=hs.getValue("utype").toString();
    if(!uid.equals(""))
	{
	if (utype.equals("Admin"))
	 sid=req.getParameter("sid");
	else if (utype.equals("Supplier"))
	 sid=cname;
	if (!sid.equals(""))
			{
	v=S.ShowSuppliers(sid);
  	pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE>");
	pw.println("</head><P align=center><FONT color=deepskyblue size=4><STRONG>SUPPLIERS INFORMATION</STRONG></FONT></P> ");
	pw.println("<body><br><br>");
	pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
	pw.println("<TR><TD>Supplier ID</TD><TD>"+sid+"</TD></TR>");
	pw.println("<TR><TD>Suppliers Name&nbsp;</TD><TD>"+v.get(1).toString()+"</TD></TR><TR><TD>SupplierAddress&nbsp;</TD>");
	pw.println("<TD>"+v.get(2).toString()+"</TD></TR><TR><TD>CreditLimit </TD><TD>"+v.get(3).toString()+"</TD></TR><TR><TD>Status</TD><TD>"+v.get(4).toString());
    pw.println("</TD></TR> <TR> <TD><P>Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </P></TD><TD>"+v.get(5).toString()+"</TD></TR>");
   if (utype.equals("Admin"))
	pw.println("<TR><TD ALIGN=CENTER><A HREF='./getsuppliers'>continue</a></table></center></body></html>");
   else if (utype.equals("Supplier"))
    pw.println("</table></center></body></html>");
			pw.flush();
			pw.close();
			}
    }
			}catch(Exception e){}
			}
			
}