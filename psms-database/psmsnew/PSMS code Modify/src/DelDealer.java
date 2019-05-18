import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class DelDealer extends HttpServlet
{
    Connection con=null;

	webman.Dealer D=new webman.Dealer();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        D.delDealer(req.getParameter("did"));
            pw.println("<html><head><TITLE>Customer Relation Ship Management</TITLE></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>User Name :</td><td>"+req.getParameter("did")+"</td></tr>");

			pw.println("<tr><td>Dealer data is deleted Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./deletedealer' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}