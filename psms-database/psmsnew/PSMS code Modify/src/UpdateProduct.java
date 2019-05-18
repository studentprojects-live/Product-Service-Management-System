import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class UpdateProduct extends HttpServlet
{
    Connection con=null;

	webman.Product D=new webman.Product();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        D.modifyProduct(req.getParameter("prid"),req.getParameter("prname"),req.getParameter("prdesc"),Double.parseDouble(req.getParameter("price")),req.getParameter("status"));
            pw.println("<html><head><title>Product Modified</title></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Product ID :</td><td>"+req.getParameter("prid")+"</td></tr>");

			pw.println("<tr><td>Product data is modified Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./modifyproduct' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}