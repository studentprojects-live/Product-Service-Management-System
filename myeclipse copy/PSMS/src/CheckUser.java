import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class CheckUser extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
    String uid;
    String pwd;
	String utype,cname;
	Vector v=new Vector();
	webman.User U=new webman.User();
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
				uid=req.getParameter("uid");
				pwd=req.getParameter("pwd");
				utype=req.getParameter("utype");
				System.out.println(uid);
			if(U.validUser(uid,pwd,utype)==true)
			{
				//hs.setAttribute("userid",uid);
				
			v=U.ShowUser(uid);
			ServletContext context=getServletContext();
			LoggedInUsers log=new LoggedInUsers(context,uid);
			boolean b=log.getExistingUser();
			if(b)
			{
				pw.println("<html><head><title>STAFF CHECK</title></head>");
				pw.println("<body><br><br>");
				pw.println("<h3 align=center>Sorry! the user has already logged</h3>");
				pw.println("<br> <pre>                   </pre><a href='http://localhost:65535/product'>Try Again</a>");	
			}
			else{
				context.setAttribute(uid,uid);
			hs.setAttribute("uid",uid);
			hs.setAttribute("password", pwd);
			hs.putValue("uid",uid);
			hs.putValue("pwd",pwd);
			hs.putValue("cname",cname=(String)v.elementAt(0));
			hs.putValue("utype",utype=(String)v.elementAt(3));
			pw.println("welcome to "+hs.getAttribute("uid"));
            if (utype.equals("Admin"))
            {
            pw.println("<html><head><TITLE>Products And Services Management  Syste</TITLE></head>");
			pw.println("<body><br><br><form name=useradm action='./Frame.htm'>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr><th colspan=2>Products And Services Management  Syste</th></tr>");
			pw.println("<tr><td align=right>User Name :</td><td>"+uid+"</td></tr>");
			pw.println("<tr><td align=right>Member of :</td><td>"+utype+"</td></tr>");
			pw.println("<tr><td colspan=2 align=center><input type='submit' name='submit' value='Submit'></td>");
			pw.println("</table></form></body></html>");
			}else
            if (utype.equals("Dealer"))
            {
            pw.println("<html><head><title>DEALER CHECK</title></head>");
			pw.println("<body><br><br><form name=userDealer action='./DFrame.htm'>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr><th colspan=2>Products And Services Management  Syste</th></tr>");
			pw.println("<tr><td align=right>User Name :</td><td>"+uid+"</td></tr>");
			pw.println("<tr><td align=right>Member of :</td><td>"+utype+"</td></tr>");
			pw.println("<tr><td colspan=2 align=center><input type='submit' name='submit' value='Submit'></td>");
			pw.println("</table></form></body></html>");
			}else
			if (utype.equals("Supplier"))
            {
            pw.println("<html><head><title>SUPPLIER CHECK</title></head>");
			pw.println("<body><br><br><form name=userSupplier action='./SFrame.htm'>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr><th colspan=2>Products And Services Management  Syste</th></tr>");
			pw.println("<tr><td align=right>User Name :</td><td>"+uid+"</td></tr>");
			pw.println("<tr><td align=right>Member of :</td><td>"+utype+"</td></tr>");
			pw.println("<tr><td colspan=2 align=center><input type='submit' name='submit' value='Submit'></td>");
			pw.println("</table></form></body></html>");
			}else
			if (utype.equals("Staff"))
            {
            pw.println("<html><head><title>STAFF CHECK</title></head>");
			pw.println("<body><br><br><form name=userSupplier action='./StFrame.htm'>");
			pw.println("<table align='center' border=0>");
			pw.println("<tr><th colspan=2>Products And Services Management  Syste</th></tr>");
			pw.println("<tr><td align=right>User Name :</td><td>"+uid+"</td></tr>");
			pw.println("<tr><td align=right>Member of :</td><td>"+utype+"</td></tr>");
			pw.println("<tr><td colspan=2 align=center><input type='submit' name='submit' value='Submit'></td>");
			pw.println("</table></form></body></html>");
			}
			}
			}
			else {
				pw.println("<html><head><title>STAFF CHECK</title></head>");
				pw.println("<body><br><br>");
				pw.println("<h3 align=center>Sorry! Login failed. Invalid userid/password</h3>");
				pw.println("<br> <pre>                   </pre><a href='http://localhost:65535/PSMS'>Try Again</a>");
			}			
			pw.flush();
			pw.close();
			}
		}
        
