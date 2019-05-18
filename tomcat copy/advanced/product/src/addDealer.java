import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class addDealer extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	webman.Dealer U=new webman.Dealer();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		try{
			if (U.createDealer(req.getParameter("did"),req.getParameter("dname"),req.getParameter("daddr"),Double.parseDouble(req.getParameter("cl")),req.getParameter("status"))==true)
			{
		        pw.println("<html><head><TITLE>Products And Services Management System</TITLE></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Products And Services Management System</th></tr>");
			pw.println("<tr><td>Dealer Id :</td><td>"+req.getParameter("did")+"</td></tr>");
			pw.println("<tr><td>Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./AddDealer.html' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			}
}catch(Exception e){}
			}
            
}