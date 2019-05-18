import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class UpdateProductStock extends HttpServlet
{
    Connection con=null;

	webman.ProductStock PRS=new webman.ProductStock();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        PRS.modifyProductStock(req.getParameter("pid"),req.getParameter("did"),Integer.parseInt(req.getParameter("qty")),Integer.parseInt(req.getParameter("rol")));
            pw.println("<html><head><title>ProductStock Modified</title></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Dealer ID :</td><td>"+req.getParameter("did")+"</td></tr>");

			pw.println("<tr><td>ProductStock data is modified Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./modifyproductstock' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}