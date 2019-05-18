import webman.*;
import java.io.*;

import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class ProductionSchedule extends HttpServlet
{
	HttpSession hs=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		hs=req.getSession(false);
		PrintWriter out=res.getWriter();
		out.println("WELCOME  TO    "+hs.getAttribute("uid"));
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String UserId = hs.getValue("uid").toString();
		String Product=null;
		Vector vqtyreq =new Vector();
		int qtyreq = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		webman.QtyRequired qr=null;
		String P=null;
		try {
		con = getConnection();
		ps = con.prepareStatement("select ProductId,sum(Quantity) from ProductTrans where Comments='Pending' group by ProductId order by ProductId ");
		ps.executeQuery();
		rs = ps.getResultSet();
		if(rs.next())
		{
System.out.println("select ProductId,sum(Quantity) from ProductTrans where Comments='Pending' group by ProductId order by ProductId is executed");
			do
			{
				P = rs.getString(1);
				qr = new webman.QtyRequired();
			    qr.setProductId(P);
				qr.setQuantity(rs.getInt(2));
System.out.println("ProductId = " + P);
				vqtyreq.addElement(qr);
			}while(rs.next());

			  ps.close();  
		  	  String cmd= "select productid,abs(Quantity-ReoderLevel) from ProductStock where dealerid='D002'";
		  	  Statement stmt = con.createStatement();
		      rs = stmt.executeQuery(cmd);

			  String pid=null;
			  int qty=0;
		      while (rs.next()) 
			  {
System.out.println(cmd);
				  pid=rs.getString(1);
				  qty=rs.getInt(2);
				   for(int i=0;i < vqtyreq.size();i++)
				  {     
System.out.println("Pid = " + pid + "Vectore Pid = " + ((webman.QtyRequired)vqtyreq.elementAt(i)).getProductId());
						if(pid.equals(((webman.QtyRequired)vqtyreq.elementAt(i)).getProductId()))
					  {
System.out.println("True");
							qtyreq = Math.abs(((webman.QtyRequired)vqtyreq.elementAt(i)).getQuantity() - qty);
System.out.println(qtyreq);
							((webman.QtyRequired)vqtyreq.elementAt(i)).setQuantity(qtyreq);
							break;
					  }
				  }
			  	  System.out.println();
			  }

			pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
			pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>PRODUCTION&nbsp;SCHEDULE</STRONG></FONT></P> ");
			pw.println("<form name='ProductionSchedule' method=post action='./partsindent'>");
			pw.println("<table align=center border=0><tr><th>Product Id</th><th>Quantity</th></tr>");
			for(int i=0;i < vqtyreq.size();i++)
			{
				pw.println("<TR><TD>" + ((webman.QtyRequired)vqtyreq.elementAt(i)).getProductId() + "</TD>");
				pw.println("<TD>" + ((webman.QtyRequired)vqtyreq.elementAt(i)).getQuantity() + "</TD></TR>");
		   }

		    pw.println("<tr><td><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value='Generate Parts Requirements'></td></tr>");
		    pw.println("</table></FORM></BODY></HTML>");
			hs.putValue("vproreq",vqtyreq);
		}
	     else
		 {
			pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
			pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>PRODUCTION&nbsp;SCHEDULE</STRONG></FONT></P> ");
			pw.println("<P align=center><FONT color=blue size=4><STRONG>NO PENDING ORDERS</STRONG></FONT></P> ");
			pw.println("</BODY></HTML>");
		 }
	} catch (Exception sqe) {
      log("SQLException: " + sqe);
      sqe.printStackTrace();
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
