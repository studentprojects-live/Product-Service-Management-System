import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class PartsIndent extends HttpServlet
{
	HttpSession hs=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   		hs=req.getSession(true);
		String UserId = hs.getValue("uid").toString();
		Vector vproreq = (Vector)hs.getValue("vproreq");
		String Product;
		Vector vpartsreq =new Vector();
		int qtyreq = 0; 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		webman.PartRequired pr=null;

    try {
		con = getConnection();
		ps = con.prepareStatement("select PartId from Parts order by PartId ");
		ps.executeQuery();
		rs = ps.getResultSet();

		while(rs.next())
		{
		    pr = new webman.PartRequired();
		    pr.setPartId(rs.getString(1));
			pr.setQuantity(0);
			vpartsreq.addElement(pr);
		}

	  ps = con.prepareStatement("select PartId, NoRequired from BillOfMaterials  where ProductId = ?");

	  int j = 0;
	  String pid=null;
	  int reqr=0;
	  for(int i = 0; i < vproreq.size();i++)
		{
		   ps.setString(1,((webman.QtyRequired)vproreq.elementAt(i)).getProductId());
           ps.executeQuery();
           rs = ps.getResultSet();
	  
			while (rs.next()) 
			{
				pid=rs.getString(1);
				reqr=rs.getInt(2);
				for(j=0; j < vpartsreq.size();j++)
				{
					if(pid.equals(((webman.PartRequired)vpartsreq.elementAt(j)).getPartId()))
					{
						qtyreq = ((webman.PartRequired)vpartsreq.elementAt(j)).getQuantity() + (reqr * ((webman.QtyRequired)vproreq.elementAt(i)).getQuantity());
						((webman.PartRequired)vpartsreq.elementAt(j)).setQuantity(qtyreq);
						break;
					}
				}
			}
		}


		ps = con.prepareStatement("select PartId, abs(Quantity - ReorderLevel ) from Parts order by PartId ");
		ps.executeQuery();
		rs = ps.getResultSet();
        
		while(rs.next())
		{
				pid=rs.getString(1);
				reqr=rs.getInt(2);
				for(j=0; j < vpartsreq.size();j++)
				{
					if(pid.equals(((webman.PartRequired)vpartsreq.elementAt(j)).getPartId()))
					{
	
							qtyreq = Math.abs(((webman.PartRequired)vpartsreq.elementAt(j)).getQuantity() - reqr);
							if(qtyreq < 0)
								((webman.PartRequired)vpartsreq.elementAt(j)).setQuantity(0);
							else
							((webman.PartRequired)vpartsreq.elementAt(j)).setQuantity(qtyreq);
							break;
					}
				}
		}

		
		ps = con.prepareStatement("Insert into PartsReq values(?,?,?,?)");

		java.util.Date TDate = new java.util.Date();
		String PartOrderId = "";
		for(j=0; j < vpartsreq.size();j++)
		{		
			PartOrderId = ((webman.PartRequired)vpartsreq.elementAt(j)).getPartId() + TDate.toString();
			ps.setString(1, PartOrderId);
			ps.setString(2, ((webman.PartRequired)vpartsreq.elementAt(j)).getPartId());
			ps.setInt(3, ((webman.PartRequired)vpartsreq.elementAt(j)).getQuantity());
			ps.setString(4,"Pending");

			ps.executeUpdate();
		}

		ps = con.prepareStatement("select * from PartsReq where Comments='Pending' order by PartId ");
		ps.executeQuery();
		rs = ps.getResultSet();
	  if(rs.next())
	  {
        pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD>");
		pw.println("<BODY><P align=center><FONT color=deepskyblue size=4><STRONG>PARTS&nbsp;INDENTS</STRONG></FONT></P>");
		pw.println("<form name=requoto method=post action='./requestquotations'>");
		pw.println("<table align=center border=0><tr><th>PartOrderId</th><th>PartId</th><th>Quantity</th></tr>");
	do
	{
		pw.println("<TR><TD>" + rs.getString(1) + "</TD>");
		pw.println("<TD>" + rs.getString(2) + "</TD>");
		pw.println("<TD>" + rs.getInt(3) + "</TD></TR>");
   }while(rs.next());

	 pw.println("<tr><td><INPUT id=submit1 name=submit1 style='LEFT: 151px; TOP: 318px' type=submit value='Send Mails / Request Quotations'></td><td></td></tr>");
	 pw.println("</table></FORM></BODY></HTML>");
	
	}
	else
	{
pw.println("<HTML><HEAD><TITLE>Web-Enabled Automated Manufacturing System</TITLE></HEAD><BODY>");
pw.println("<P align=center><FONT color=deepskyblue size=4><STRONG>PARTS&nbsp;INDENT</STRONG></FONT></P> ");
pw.println("<P align=center><FONT color=blue size=4><STRONG>NO PARTS REQUIRED</STRONG></FONT></P> ");
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
