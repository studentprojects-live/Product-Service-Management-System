import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class UpdateUser extends HttpServlet
{
    Connection con=null;

	webman.User U=new webman.User();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
        U.modifyUser(req.getParameter("name"),req.getParameter("pwd"),req.getParameter("logtype"),req.getParameter("eid"),req.getParameter("uid"));
            pw.println("<html><head><title>User Modified</title></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management/th></tr>");
			pw.println("<tr><td>User Name :</td><td>"+req.getParameter("uid")+"</td></tr>");

			pw.println("<tr><td>User data is modified Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='./modifyuser' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}
            
}