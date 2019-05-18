import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class SubmitOrderDB extends HttpServlet
{
	HttpSession hs=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String DealerId = hs.getValue("DealerId").toString();
		Vector v = (Vector)hs.getValue("ProductIds");
		java.sql.Date TDate = new java.sql.Date(1,2,3);
		int qty;
		Connection con = null;
		PreparedStatement ps = null;
	    ResultSet rs=null;
    try {
		con = getConnection();
 	    ps = con.prepareStatement("insert into ProductTrans values(?,?,?,?,?)");

	for(int i = 0; i < v.size();i++)
	{
		qty = Integer.parseInt(req.getParameter("Q"+i));
  	    ps.setString(1, DealerId);	
   	    ps.setString(2, (String)v.get(i));	
		ps.setDate(3,new java.sql.Date(TDate.getTime()));
   	    ps.setInt(4, qty);	
		ps.setString(5, "Pending");	
        ps.executeUpdate();		
      }
pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>SUBMIT ORDERS</STRONG></FONT></P> ");
pw.println("<P align=center><FONT color=blue size=4><STRONG>PRODUCT TRANSACTION IS UPDATED</STRONG></FONT></P> ");
pw.println("</BODY></HTML>");

	
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
		log("UNABLE to get a connection");
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
