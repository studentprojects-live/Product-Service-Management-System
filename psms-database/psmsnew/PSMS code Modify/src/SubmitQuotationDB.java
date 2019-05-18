import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class SubmitQuotationDB extends HttpServlet
{
	HttpSession hs=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		String sysDate=null;
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String SupplierId = hs.getValue("SupplierId").toString();
		int rate=Integer.parseInt(req.getParameter("rate"));

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		Vector PartOrderIds = (Vector)hs.getValue("PartOrderIds");
		System.out.println("Vector size is: " +PartOrderIds.size());
		int qty=0;
		System.out.println(SupplierId);
				
		Connection con = null;
		PreparedStatement ps = null;
		Statement statement = null;
		ResultSet rs;

	try {
		con = getConnection();
	    ps = con.prepareStatement("insert into Quotations(SupplierId, PartOrderId, Rate, Status) values(?,?,?,?)");
		System.out.println("SupplierId");
	for(int i = 0; i < PartOrderIds.size();i++)
	{
		System.out.println("HelloWorld");
		System.out.println("i" +i);
		qty = i;
		System.out.println("qty");
		
		 String partOrderID=(String)PartOrderIds.elementAt(i);  
		  		  
		  ps.setString(1, SupplierId);	
		  System.out.println("supplierid");
   	
		  ps.setString(2, partOrderID);
		  System.out.println("hello");
		
		  ps.setInt(3, rate);	
		  System.out.println("qty");
		  
		  ps.setString(4, "Pending");
		  System.out.println("pending");

			 if (ps.executeUpdate()>0) System.out.println("updated");		
      }

pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>SUBMIT QUOTATION</STRONG></FONT></P> ");
pw.println("<P align=center><FONT color=blue size=4><STRONG>QUOTATIONS ARE SUBMITED</STRONG></FONT></P> ");
pw.println("</BODY></HTML>");

	} catch (SQLException sqe) {
		System.out.println("In SubmitQuotationDB.java Exception occured" +sqe);
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
