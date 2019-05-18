import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class showDealer1 extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String uid;
	webman.Dealer D=new webman.Dealer();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		uid=req.getParameter("did");
			if (!uid.equals(""))
			{
			v=D.getDealer(uid);
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE><script language=javascript>function set() {");
			pw.println("document.deldealer.select1.value='"+(String)v.elementAt(4)+"'} </script></head><P align=center><FONT color=deepskyblue size=4><STRONG>MODIFY&nbsp;DEALER </STRONG></FONT></P> ");
			pw.println("<body onLoad=set()><br><br><form name=deldealer method=post action='./deldealer'>");
			pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
			pw.println("<TR><TD>DealerId&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD><TD><INPUT id=text1 name=did value="+(String)v.elementAt(0)+"></TD></TR>");
			pw.println("<TR><TD>DealerName</TD><TD><INPUT id=text2 name=dname value="+(String)v.elementAt(1)+" ></TD></TR><TR><TD>DealerAddress</TD>");
		    pw.println("<TD><INPUT id=text2 type=text name=daddr value="+(String)v.elementAt(2)+"></TD></TR><TR><TD>CreditLimit</TD><TD><INPUT id=text4 name=cl value="+v.get(3).toString());
            pw.println("></TD></TR><TR><TD><P>Staus</P></TD><td><SELECT id=select1 name=status style='HEIGHT: 22px; LEFT: 1px; TOP: 1px; WIDTH: 136px'> <OPTION ");
            pw.println("selected value=''></OPTION><OPTION value=Active>Active</OPTION><OPTION value=Inactive>Inactive</OPTION></SELECT><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value=Delete></TD></TR>");
			pw.println("</table></center></form></body></html>");
			pw.flush();
			pw.close();
			}
			}
			
            
}