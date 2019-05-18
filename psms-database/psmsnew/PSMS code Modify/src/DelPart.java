import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class DelPart extends HttpServlet
{
    Connection con=null;

	webman.Part P=new webman.Part();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        P.deletePart(req.getParameter("pid"));
            pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Part ID :</td><td>"+req.getParameter("pid")+"</td></tr>");

			pw.println("<tr><td>Part data is deleted Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./deletepart' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}