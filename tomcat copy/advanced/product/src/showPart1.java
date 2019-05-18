import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class showPart1 extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String pid;
	webman.Part P=new webman.Part();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		pid=req.getParameter("pid");
			if (!pid.equals(""))
			{
			v=P.ShowPart(pid);
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE><script language=javascript>function set() {");
			pw.println("document.delPart.select1.value='"+v.get(3).toString()+"'} </script></head><P align=center><FONT color=deepskyblue size=4><STRONG>DELETE&nbsp;PART</STRONG></FONT></P> ");
			pw.println("<body onLoad=set()><form name=delPart method=post action='./delpart'>");
			pw.println("<center><TABLE border=0 cellPadding=1 cellSpacing=1 width='75%' style='HEIGHT: 147px; WIDTH: 248px'>");
			pw.println("<TR><TD>Part Id</TD><TD><INPUT id=text1 name=pid value="+v.get(0).toString()+"></TD></TR>");
			pw.println("<TR><TD>Part Name&nbsp;</TD><TD><INPUT id=text2 name=pname value="+v.get(1).toString()+" ></TD></TR>");
		    pw.println("<TR><TD>Part Description </TD><TD><INPUT id=text3 name=pdesc value='"+v.get(2).toString()+"'></TD></TR> <TR> <TD><P>ReorderLevel&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </P></TD>");
            pw.println("<TD><INPUT id=text4 name=rl value="+v.get(5).toString()+"></TD></TR>");
			pw.println("<TR><TD>Quantity&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </P></TD>");
            pw.println("<TD><INPUT id=text4 name=qty value="+v.get(4).toString()+"></TD></TR>");
            pw.println("<TR><TD><P>Status </p></TD><TD><SELECT id=select1   style=LEFT: 74px; WIDTH: 155px; TOP: 222px; HEIGHT: 22px name=status>");
			pw.println("<OPTION value='' selected></OPTION><OPTION  value=Active>Active</OPTION><OPTION value=Inactive>Inactive</OPTION></SELECT><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value=Delete></TD></TR>");
			pw.println("</table></center></form></body></html>");
			pw.flush();
			pw.close();
			}
			}
			
            
}