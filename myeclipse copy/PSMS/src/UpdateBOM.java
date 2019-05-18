import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class UpdateBOM extends HttpServlet
{
    Connection con=null;

	webman.BillOfMaterials B=new webman.BillOfMaterials();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        B.modifyBOFM(req.getParameter("prid"),req.getParameter("pid"),Integer.parseInt(req.getParameter("req")));
        System.out.println("llll" + req.getParameter("prid")+req.getParameter("pid")+Integer.parseInt(req.getParameter("req")));
            pw.println("<html><head><title>Bill of Materials Modified</title></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Product ID :</td><td>"+req.getParameter("prid")+"</td></tr>");
			pw.println("<tr><td>Bill of Material data is modified Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./modifybom' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}