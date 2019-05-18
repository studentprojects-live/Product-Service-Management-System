import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class SubmitQuotation extends HttpServlet
{
	HttpSession hs=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String UserId = hs.getValue("uid").toString();
		String PartOId=null;
		String deliveryDate=null;
		String SupplierId=null;
		Vector PartOrders=new Vector();

		Connection con = null;
		PreparedStatement ps = null;
			    ResultSet rs;
    try {
		con = getConnection();
		ps = con.prepareStatement("select CompanyId from Users where LoginName = ?");
		ps.setString(1, UserId);	
		ps.executeQuery();
		rs = ps.getResultSet();
		if(rs.next())
		 SupplierId=rs.getString(1);
		 ps.close();
		 System.out.println(SupplierId);
   	     ps = con.prepareStatement("select * from PartsReq where Comments = 'Pending'");
         ps.executeQuery();
         rs = ps.getResultSet();
		if(rs.next())
		{
	     pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
         pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>Submit&nbsp;Quotation in Figures</STRONG></FONT></P>");
         pw.println("<form name=SubmitQuotation method=post action='./submitquotationdb'>");
         pw.println("<table align=center border=0><tr><th>PartOrderId</th><th>Rate</th></tr>");
		 int i = 0;
		  do
			{
		        PartOId = rs.getString(1);
				System.out.println(PartOId);
				
				pw.println("<tr><td><INPUT id=text1 READONLY name='PO"+i+"' value='"+PartOId+"'></td><td>");
				pw.println("<INPUT id=text2 type=text name=rate value="+i+"></td></tr>");
				PartOrders.addElement(PartOId);
				i++;
		      }while (rs.next()) ;

	  hs.putValue("PartOrderIds",PartOrders);
	  hs.putValue("SupplierId",SupplierId);
	  pw.println("<tr><td><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value='Submit Quotation'></td><td></td></tr>");
	 pw.println("</table></FORM></BODY></HTML>");
	}
	else
	{
pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>SUBMIT QUOTATION</STRONG></FONT></P> ");
pw.println("<P align=center><FONT color=blue size=4><STRONG>Sorry. No Requirement for Parts...</STRONG></FONT></P> ");
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
