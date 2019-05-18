import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class modifyBOM extends HttpServlet
{
    Connection con=null;
	HttpSession hs=null;
	Vector v=new Vector();
	Vector v1=new Vector();
	int i;
	String id;
	webman.Part p=new webman.Part();
	webman.Product pr=new webman.Product();
	webman.BillOfMaterials B=new webman.BillOfMaterials();
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		try
		{
 		    PrintWriter pw=res.getWriter();
			pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE></head>");
			pw.println("<body><P align=center><FONT color=deepskyblue size=4><STRONG>MODIFY BILL OF MATERIALS DETAILS</STRONG></FONT></P>");
            pw.println("<form name=modifybom method=post action='./showbom'>");
            v=pr.allProducts();
			v1=p.allParts();
		    pw.println("<table align='center' border=0> <tr><td>");
			pw.println("Select Product ID</td><td><SELECT id=select1 name=prid style='HEIGHT: 22px; LEFT: 74px; TOP: 222px; WIDTH: 155px'>"); 
			pw.println("<OPTION selected value=''></OPTION>");
			for(i=0;i<v.size();i++)
				pw.println("<OPTION value="+(String)v.elementAt(i)+">"+(String)v.elementAt(i)+"</OPTION>");
     		pw.println("</SELECT></td></tr>");
			pw.println("<tr><td>Select Part ID</td><td><SELECT id=select2 name=pid style='HEIGHT: 22px; LEFT: 74px; TOP: 222px; WIDTH: 155px'>"); 
			pw.println("<OPTION selected value=''></OPTION>");
			for(i=0;i<v1.size();i++)
				pw.println("<OPTION value="+(String)v1.elementAt(i)+">"+(String)v1.elementAt(i)+"</OPTION>");
     		pw.println("</SELECT></td></tr><tr><td></td><td><input type='submit' name='submit' value='Submit'></td></tr></table></form></body></html>");
			pw.flush();
			pw.close();

			}catch(Exception e){}
	}

}