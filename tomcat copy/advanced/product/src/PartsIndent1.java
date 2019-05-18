import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class PartsIndent1 extends HttpServlet
{
	HttpSession hs=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		
    try {
		con = getConnection();
		ps = con.prepareStatement("select * from PartsReq where Comments='Pending' order by PartId ");
		ps.executeQuery();
		rs = ps.getResultSet();
	  if(rs.next())
	  {
        pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD>");
		pw.println("<BODY><P align=center><FONT color=deepskyblue size=4><STRONG>PARTS&nbsp;INDENTS DETAILS</STRONG></FONT></P>");
		pw.println("<form name=requoto method=post action='./requestquotations'>");
		pw.println("<table align=center border=1><tr><th>PartOrderId</th><th>PartId</th><th>Quantity</th></tr>");
	do
	{
		pw.println("<TR><TD>" + rs.getString(1) + "</TD>");
		pw.println("<TD>" + rs.getString(2) + "</TD>");
		pw.println("<TD>" + rs.getInt(3) + "</TD></TR>");
   }while(rs.next());

	 pw.println("</table></FORM></BODY></HTML>");
	
	}
	else
	{
pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>PARTS&nbsp;INDENT DETAILS</STRONG></FONT></P> ");
pw.println("<P align=center><FONT color=blue size=4><STRONG>NO PARTS INDENTS FOUND </STRONG></FONT></P> ");
pw.println("</BODY></HTML>");

	}
	} catch (SQLException sqe) {
      log("SQLException: " + sqe);
    } finally {
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
