import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class addPart extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	webman.Part P=new webman.Part();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		PrintWriter pw=res.getWriter();
		hs=req.getSession(false);
		pw.println("WELCOME TO"+hs.getAttribute("uid"));
	 try{
		res.setContentType("text/html");
		
			if (P.createPart(req.getParameter("pid"),req.getParameter("pname"),req.getParameter("pdesc"),req.getParameter("status"),Integer.parseInt(req.getParameter("qty")),Integer.parseInt(req.getParameter("rl")))==true)
			{
            pw.println("<html><head><TITLE>Customer Relation Ship Management</TITLE></head>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr col span=2><th>Customer Relation Ship Management</th></tr>");
			pw.println("<tr><td>Part ID :</td><td>"+req.getParameter("pid")+"</td></tr>");
			pw.println("<tr><td>Part Name:</td><td>"+req.getParameter("pname")+"</td></tr>");
			pw.println("<tr><td>Description:</td><td>"+req.getParameter("pdesc")+"</td></tr>");
			pw.println("<tr><td>ReOrderLevel:</td><td>"+req.getParameter("rl")+"</td></tr>");
			pw.println("<tr><td>Quantity :</td><td>"+req.getParameter("qty")+"</td></tr>");
			pw.println("<tr><td>Status:</td><td>"+req.getParameter("status")+"</td></tr>");
			pw.println("<tr><td>Click on OK to Continue</td></tr>");
			pw.println("<tr><td align=center><a href='AddPart.html' target='main'>OK</a></td>");
			pw.println("<td></td></tr>");
			pw.println("</table></form></body></html>");
			pw.flush();
			pw.close();
			
			}}catch(Exception e){
				pw.println("<font color=red>");
				pw.println("ENTER CORRECT DATA");}
			}
            
}