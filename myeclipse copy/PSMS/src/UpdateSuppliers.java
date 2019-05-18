import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class UpdateSuppliers extends HttpServlet
{
    Connection con=null;

	webman.Suppliers S=new webman.Suppliers();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        S.modifySuppliers(req.getParameter("sname"),req.getParameter("sadd"),Double.parseDouble(req.getParameter("climit")),req.getParameter("status"),req.getParameter("eid"),req.getParameter("sid"));
            pw.println("<html><head><title>Suppliers Modified</title></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Suppliers ID :</td><td>"+req.getParameter("sid")+"</td></tr>");

			pw.println("<tr><td>Suppliers data is modified Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./modifysuppliers' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}