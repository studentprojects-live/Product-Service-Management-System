import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class addProductStock extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	webman.ProductStock PRS=new webman.ProductStock();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		try{
			if(PRS.createProductStock(req.getParameter("did"),req.getParameter("prid"),Integer.parseInt(req.getParameter("qty")),Integer.parseInt(req.getParameter("rol")))==true)
			{
	        pw.println("<html><head><TITLE>Customer Relation Ship Management</TITLE></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Dealer ID :</td><td>"+req.getParameter("did")+"</td></tr>");
			pw.println("<tr><td>Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./addprds' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			}
}catch(Exception e){}
			}
            
}