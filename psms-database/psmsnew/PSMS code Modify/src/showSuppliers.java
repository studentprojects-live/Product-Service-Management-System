import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class showSuppliers extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String sid;
	webman.Suppliers S=new webman.Suppliers();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
	 try{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		sid=req.getParameter("sid");
			if (!sid.equals(""))
			{
			v=S.ShowSuppliers(sid);
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE><script language=javascript>function set() {");
			pw.println("document.updateSuppliers.select1.value='"+v.get(4).toString()+"'} </script></head><P align=center><FONT color=deepskyblue size=4><STRONG>MODIFY&nbsp;SUPPLIER </STRONG></FONT></P> ");
			pw.println("<body onLoad=set()><form name=updateSuppliers method=post action='./updatesuppliers'>");
			pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
			pw.println("<TR><TD>Supplier ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD><TD><INPUT id=text1 name=sid value="+v.get(0).toString()+"></TD></TR>");
			pw.println("<TR><TD>Supplier Name&nbsp;</TD><TD><INPUT id=text2 name=sname value="+v.get(1).toString()+" ></TD></TR><TR><TD>Supplier Address&nbsp;</TD>");
		    pw.println("<TD><INPUT id=text2 type=text name=sadd value="+v.get(2).toString()+"></TD></TR><TR><TD>Credit Limit </TD><TD><INPUT id=text2 type=text name=climit value="+v.get(3).toString()+"></TD></tr><TR><TD>Status</TD><TD><SELECT id=select1 name=status style='HEIGHT: 22px; LEFT: 1px; TOP: 1px; WIDTH: 136px'> <OPTION ");
            pw.println("selected value=''></OPTION><OPTION value=Inactive>Inactive</OPTION><OPTION value=Active>Active</OPTION></SELECT></TD></TR> <TR> <TD><P>Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </P></TD>");
            pw.println("<TD><INPUT id=text4 name=eid value="+v.get(5).toString()+"><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value=Update></TD></TR>");
			pw.println("</table></center></form></body></html>");
			pw.flush();
			pw.close();
			}
			}catch(Exception e){}
			}
			
            
}