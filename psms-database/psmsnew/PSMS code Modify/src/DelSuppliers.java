import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class DelSuppliers extends HttpServlet
{
    Connection con=null;

	webman.Suppliers S=new webman.Suppliers();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        S.deleteSuppliers(req.getParameter("sid"));
            pw.println("<html><head><TITLE>Produts And Services Management System</TITLE></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Produts And Services Management System</th></tr>");
			pw.println("<tr><td>Suppliers ID :</td><td>"+req.getParameter("sid")+"</td></tr>");
			pw.println("<tr><td>Supplier data is deleted Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./deletesuppliers' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}