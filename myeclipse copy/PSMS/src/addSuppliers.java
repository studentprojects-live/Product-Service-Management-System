import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class addSuppliers extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	webman.Suppliers S=new webman.Suppliers();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		try{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        hs=req.getSession(false);
			if (S.createSuppliers(req.getParameter("sid"),req.getParameter("sname"),req.getParameter("sadd"),Double.parseDouble(req.getParameter("climit")),req.getParameter("status"),req.getParameter("eid"))==true)
			{
            pw.println("<html><head><TITLE>Products And Services Management System</TITLE></head>");
            pw.println("WELCOME TO"+hs.getAttribute("uid"));
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Products And Services Management System</th></tr>");
			pw.println("<tr><td>Suppliers ID :</td><td>"+req.getParameter("sid")+"</td></tr>");
			pw.println("<tr><td>Supplier Name :</td><td>"+req.getParameter("sname")+"</td></tr>");
			pw.println("<tr><td>Supplier Address  :</td><td>"+req.getParameter("sadd")+"</td></tr>");
			pw.println("<tr><td>Credit Limit :</td><td>"+req.getParameter("climit")+"</td></tr>");
			pw.println("<tr><td>Status :</td><td>"+req.getParameter("status")+"</td></tr>");
			pw.println("<tr><td>Email:</td><td>"+req.getParameter("eid")+"</td></tr>");
			pw.println("<tr><td>Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./AddSuppliers.html' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			}
			}
			catch(Exception e){}
			}
            
}