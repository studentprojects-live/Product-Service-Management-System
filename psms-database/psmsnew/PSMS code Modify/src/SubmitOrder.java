import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class SubmitOrder extends HttpServlet
{
	HttpSession hs=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String UserId = hs.getValue("uid").toString();
		String Product=null;
		String DealerId=null;
		Vector v=new Vector();
		Connection con = null;
		PreparedStatement ps = null;
	    ResultSet rs=null;
    try {
		con = getConnection();
		ps = con.prepareStatement("select CompanyId from Users where LoginName = ?");
		ps.setString(1, UserId);	
		ps.executeQuery();
		rs = ps.getResultSet();
		if(rs.next())
 		  DealerId=rs.getString(1);


		ps.close();
		System.out.println(DealerId);
	    ps = con.prepareStatement("select ProductId from ProductStock where DealerId ='"+DealerId+"'");
        ps.executeQuery();
        rs = ps.getResultSet();
        
		
pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>SUBMIT&nbsp;ORDER QUANTITIES</STRONG></FONT></P>");
pw.println("<form name=submitorder method=post action='./submitorderdb'>");
pw.println("<table align=center border=0><tr><th>Product Id</th><th>Quantity</th></tr>");
	int i = 0;
      while (rs.next()) 
	  {
        Product = rs.getString(1);
		pw.println("<tr><td><INPUT id=text1 READONLY name='P"+i+"' value='"+Product+"'></td><td>");
		pw.println("<INPUT id=text2 type=text name='Q"+i+"'></td></tr>");
		v.addElement(Product);
		i++;
      }
	  hs.putValue("ProductIds",v);
	  hs.putValue("DealerId",DealerId);
     pw.println("<tr><td><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value='Submit Order Quantities'></td><td></td></tr>");
	 pw.println("</table></FORM></BODY></HTML>");
	
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
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:webman");
				return myConnection;
		} catch(Exception ne) 
		{
		log("UNABLE to get a connection!");
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
