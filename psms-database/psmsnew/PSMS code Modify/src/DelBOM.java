import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class DelBOM extends HttpServlet
{
    Connection con=null;

	webman.BillOfMaterials B=new webman.BillOfMaterials();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
			B.delBOFM(req.getParameter("prid"),req.getParameter("pid"));
            pw.println("<html><head><TITLE>Customer Relation Ship Management</TITLE></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Product ID :</td><td>"+req.getParameter("prid")+"</td></tr>");
			pw.println("<tr><td>Bill of Material data is Deleted Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./deletebom' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}