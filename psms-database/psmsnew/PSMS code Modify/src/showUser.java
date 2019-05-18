import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class showUser extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String uid;
	webman.User U=new webman.User();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		uid=req.getParameter("uid");
			if (!uid.equals(""))
			{
			v=U.ShowUser(uid);
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE><script language=javascript>function set() {");
			pw.println("document.updateuser.select1.value='"+(String)v.elementAt(3)+"'} </script></head><P align=center><FONT color=deepskyblue size=4><STRONG>MODIFY&nbsp;USER </STRONG></FONT></P> ");
			pw.println("<body onLoad=set()><br><br><form name=updateuser method=post action='./updateuser'>");
			pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
			pw.println("<TR><TD>CompanyName&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD><TD><INPUT id=text1 name=name value="+(String)v.elementAt(0)+"></TD></TR>");
			pw.println("<TR><TD>Username&nbsp;</TD><TD><INPUT id=text2 name=uid value="+uid+" ></TD></TR><TR><TD>Password&nbsp;</TD>");
		    pw.println("<TD><INPUT id=text2 type=password name=pwd value="+(String)v.elementAt(2)+"></TD></TR><TR><TD>User Type </TD><TD><SELECT id=select1 name=logtype style='HEIGHT: 22px; LEFT: 1px; TOP: 1px; WIDTH: 136px'> <OPTION ");
            pw.println("selected value=''></OPTION><OPTION value=Admin>Admin</OPTION><OPTION value=Staff>Staff</OPTION><OPTION value=Dealer>Dealer</OPTION><OPTION ");
            pw.println("value=Supplier>Supplier</OPTION></SELECT></TD></TR> <TR> <TD><P>Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </P></TD>");
            pw.println("<TD><INPUT id=text4 name=eid value="+(String)v.elementAt(4)+"><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value=Update></TD></TR>");
			pw.println("</table></center></form></body></html>");
			pw.flush();
			pw.close();
			}
			}
			
            
}