import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class showBOM extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String did;
	String prid;
	webman.BillOfMaterials B=new webman.BillOfMaterials();
	Vector v=new Vector();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		try{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		did=req.getParameter("prid");
		prid=req.getParameter("pid");
		
if (!did.equals(""))
{
v=B.ShowBOM(did,prid);
pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></script></head><P align=center><FONT color=deepskyblue size=4><STRONG>MODIFY&nbsp;Bill Of Material </STRONG></FONT></P> ");
pw.println("<body><form name=updatebom method=post action='./updatebom'>");
pw.println("<center><TABLE align=center style='WIDTH: 324px; HEIGHT: 157px' cellSpacing=1 cellPadding=1 width=324 border=0>");
pw.println("<TR><TD>Product ID&nbsp;&nbsp;</TD><TD><INPUT id=text1 name=prid value="+(String)v.elementAt(0)+"></TD></TR><TR><TD>Part ID&nbsp;</TD><TD><INPUT id=text3 name=pid value="+(String)v.elementAt(1)+"></TD></TR>");
pw.println("<TR><TD>NO.of Parts REquirred&nbsp;&nbsp;</TD><TD><INPUT id=text2 name=req value="+(String)v.elementAt(2)+"><INPUT id=submit1 style='LEFT: 151px; TOP: 318px' type=submit value=Update name=submit1></TD></TR>");
pw.println("</table></center></form></body></html>");
pw.flush();
pw.close();
}
}catch(Exception e){}
}
           
}