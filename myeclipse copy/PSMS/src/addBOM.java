import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class addBOM extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	webman.BillOfMaterials B=new webman.BillOfMaterials();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		hs=req.getSession(false);
		try{
			if(B.createBOFM(req.getParameter("prid"),req.getParameter("pid"),Integer.parseInt(req.getParameter("req")))==true)
			{
	        pw.println("<html><head><TITLE>Customer Relation Ship Management</TITLE></head>");
	        pw.println("WELCOME TO"+hs.getAttribute("uid"));
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Product ID :</td><td>"+req.getParameter("prid")+"</td></tr>");
			pw.println("<tr><td>Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./addbofm' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			}
}catch(Exception e){}
			}
            
}