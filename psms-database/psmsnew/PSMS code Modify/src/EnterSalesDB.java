import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class EnterSalesDB extends HttpServlet
{
	HttpSession hs=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String DealerId = hs.getValue("DealerId").toString();
		Vector v = (Vector)hs.getValue("ProductIds");
		Vector qtywdealer = new Vector();
		//Vector roldealer=new Vector();
		int qty;
		int qoh;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
    try {
		con = getConnection();
		ps = con.prepareStatement("Select Quantity from ProductStock where DealerId= ?");
		ps.setString(1, DealerId);		  
		rs = ps.executeQuery();		
		
		while(rs.next())
		{
			qtywdealer.addElement(rs.getString(1));
		  //  roldealer.addElement(rs.getString(2));	
		}
    	  ps=con.prepareStatement("Update ProductStock set Quantity = ? where DealerId= ? and ProductId=?");
boolean flag=false;    	  
	for(int i = 0; i < v.size();i++)
	{
		qty = Integer.parseInt(req.getParameter("Q"+i));
				   System.out.println(qty);
		qoh = Integer.parseInt((String)(qtywdealer.elementAt(i)));
						   System.out.println(qoh);
		qoh = qoh - qty;
		//int rl=Integer.parseInt((String)(roldealer.elementAt(i)));
		/* if(rl<qoh) {
			 flag=true;		 
			 break;
		 }*/
		  ps.setInt(1, qoh);	
		  ps.setString(2, DealerId);	
   	      ps.setString(3, (String)v.elementAt(i));	
	      if (ps.executeUpdate()>0)
	      {
		   System.out.println("updated");
	      }		
      }
	
pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>ENTER SALES DETAILS</STRONG></FONT></P> ");
pw.println("<P align=center><FONT color=blue size=4><STRONG>PRODUCT STOCK IS UPDATED</STRONG></FONT></P> ");
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
