import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class RequestQuotations extends HttpServlet
{
	HttpSession hs=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String UserId = hs.getValue("uid").toString();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
    try {
		con = getConnection();
		ps = con.prepareStatement("select SupplierId,Name,Email from Suppliers where status ='Active' order by SupplierId ");
		ps.executeQuery();
		rs = ps.getResultSet();
		int count = 0;
		while(rs.next())
		{
			count++;
		}
        con.close();
		ps.close();
		
		con = getConnection();
		ps = con.prepareStatement("select SupplierId,Name,Email from Suppliers where status ='Active' order by SupplierId ");
		ps.executeQuery();
		rs = ps.getResultSet();
		
		System.out.println(count);
		String recipientsMails[ ] = new String[count];
		String recipients[ ] = new String[count];
		String msg = "We hereby inform you that there arose a requirement for some parts. Logon and submit your quotations for the Parts Indent"; 
		count = 0;
		while(rs.next())
		{
			recipients[count] = rs.getString(2); 
			recipientsMails[count] = rs.getString(3); 
			count++;
		}
				System.out.println(count);
				String coEmail=null;
		con.close();
		ps.close();
		
		con = getConnection();
		ps = con.prepareStatement("select EmailAddress from Users where LoginType='Admin'");
		ps.executeQuery();
		rs = ps.getResultSet();
		if (rs.next())
			 coEmail = rs.getString(1);
				System.out.println(coEmail);
		webman.SendMail mailsupps = new webman.SendMail(recipientsMails, "Request for Quotations", msg, coEmail);
pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD>");
pw.println("<BODY><P align=center><FONT color=deepskyblue size=4><STRONG>SENDING MAILS</STRONG></FONT></P>");
pw.println("<table align=center border=0><tr><th>SUPPLIERS</th></tr>");
	for(int i = 0; i < recipients.length; i++)
	{
		pw.println("<TR><TD>" + recipients[i] + "</TD></TR>");
    }
pw.println("</table><center>Mails has been send to Suppliers</center> </BODY></HTML>");

	} catch (SQLException sqe) {
      log("SQLException: " + sqe);
    }
	 catch (Exception e) {
      log("Exception: " + e);
	}
	finally {
      cleanup(con, ps);
    }
  }


		private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				return myConnection;
		} catch(Exception ne) 
		{
		log("UNABLE to get a connection from demoPool!");
		log("Please make sure that you have setup the connection pool properly");
		} 
		return null;
		}

  private void cleanup(Connection con, PreparedStatement ps) 
  {
	try 
	{
		if (ps != null) ps.close();
	} catch (Exception e) {
		log("Error closing PreparedStatement: "+e);
	}

	try {
	if (con != null) con.close();
	} catch (Exception e) {
	log("Error closing Connection: " + e);
	}
  }
}
