package webman;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.sql.*;

public class Dealer {

  private String DealerId, Name, Address, Status;
  private double CreditLimit;
  public Dealer(){}
  public void validDealer(String DealerId)
  {
    log("DEALER: (" + DealerId +  ")");
    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps  = con.prepareStatement("select Name, Address, CreditLimit, Status from Dealers where DealerId = ?");
      ps.setString(1, DealerId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        Name = rs.getString(1);
        Address = rs.getString(2);
        CreditLimit = rs.getDouble(3);
        Status = rs.getString(4);
      }
      else
      {
        String error = "DEALER: Dealer  (" + DealerId + ") not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
       
    } finally {
      cleanup(con, ps);
    }
  }


  public void modifyDealer(String Name, String Address, double CreditLimit, String Status,String DealerId)
  {
    log("DEALER (" + DealerId + ")");

    Connection con = null;
    PreparedStatement ps = null;

    try
    {
      con = getConnection();
      ps = con.prepareStatement("update Dealers set Name = ? , Address = ?, CreditLimit = ?, Status = ? where DealerId = ?");
      ps.setString(1, Name);
      ps.setString(2, Address);
      ps.setDouble(3, CreditLimit);
      ps.setString(4, Status);
      ps.setString(5, DealerId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "DEALER: Dealer  (" + DealerId + ") not updated";
        log(error);
         
      }
    } catch(SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }


  public boolean createDealer(String DealerId, String Name, String Address, double CreditLimit, String Status)   throws  Exception
  {
    log("Dealer Create( id = DealerId" + ", " + "initial CreditLimit = £ " + CreditLimit + ")");
    this.DealerId = DealerId;
    this.Name = Name;
    this.Address = Address;
    this.CreditLimit = CreditLimit;
    this.Status = Status;

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("insert into Dealers (DealerId, Name, Address, CreditLimit, Status)  values (?, ?, ?, ?, ?)");
      ps.setString(1, DealerId);
      ps.setString(2, Name);
      ps.setString(3, Address);
      ps.setDouble(4, CreditLimit);
      ps.setString(5, Status);

      if (ps.executeUpdate() != 1) {
        String error = "JDBC did not create any row";
        log(error);
 
      }

      return true;
    } catch (SQLException sqe) {
      try {

      } catch(Exception onfe) {
        String error = "SQLException: " + sqe;
        log(error);
 
      }
      String error = "A Dealer already exists in the database with Primary Key " + DealerId;
      log(error);
       
    }
	finally {
      cleanup(con, ps);
    }
	return false;
  }

  public void delDealer(String DealerId) 
  {
    log("Dealer Remove (" + DealerId + ")");

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("delete from Dealers where DealerId = ?");
      ps.setString(1, DealerId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "Dealer  (" + DealerId + " not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }

  public Vector getDealer(String pk)
     
  {
    log("getDealer(" + pk + ")");

    Connection con = null;
    PreparedStatement ps = null;
	Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from Dealers where Dealerid = ?");
      ps.setString(1, pk);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
	   this.DealerId=rs.getString(1);
       this.Name = rs.getString(2);
       this.Address = rs.getString(3);
       this.CreditLimit = rs.getDouble(4);
       this.Status = rs.getString(5);
       v=getDetails();
      } else 
    	{
        String error = "getDealer  (" + pk + ") not found";
        log(error);
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }

    log("getDealer (" + pk + ") found");
    return v;
  }


public Vector allDealers()
 {
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select dealerid from Dealers");
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      while (rs.next()) 
	  {
        pk = rs.getString(1);
        v.addElement(pk);
      }
      return v;
    } catch (SQLException sqe) {
      log("SQLException: " + sqe);
    } finally {
      cleanup(con, ps);
    }
  return v;
  }

public Vector allDealers(String did)
 {
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select dealerid from Dealers where DealerId='"+did+"'");
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      while (rs.next()) 
	  {
        pk = rs.getString(1);
        v.addElement(pk);
      }
      return v;
    } catch (SQLException sqe) {
      log("SQLException: " + sqe);
    } finally {
      cleanup(con, ps);
    }
  return v;
  }



  public Vector allbigDealers(double CreditLimitGreaterThan) 
  {
    log("allbigDealers (CreditLimit > " + CreditLimitGreaterThan + ")");
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
      String pk;
    try {
      con = getConnection();
      ps = con.prepareStatement("select DealerId from Dealers where CreditLimit > ?");
      ps.setDouble(1, CreditLimitGreaterThan);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
  
      while (rs.next()) {
        pk = rs.getString(1);
        v.addElement(pk);
      }
      return v;
    } catch (SQLException sqe) {
      log("SQLException: " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
	      return v;
  }

  public double updateDealer(String Name, String Address, double CreditLimit, String Status) {
    log("Updating Dealer Credit Limit changed to £" + CreditLimit + " For '" + DealerId + "'");
    this.Name = Name;
    this.Address = Address;
    this.CreditLimit = CreditLimit;
    this.Status = Status;
    return CreditLimit;
  }

  public Vector getDetails()
     
  {
    Vector v = new Vector();
    v.addElement(DealerId);
    v.addElement(Name);
    v.addElement(Address);
    v.addElement(Double.toString(CreditLimit));
    v.addElement(Status);
    return v;
  }


		private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from Dealers");
				return myConnection;
		} catch(Exception ne) 
		{
		log("UNABLE to get a connection from demoPool!");
		log("Please make sure that you have setup the connection pool properly");
		} 
		return null;
		}

  private void log(String s) {
     System.out.println(s);
  }

  private void cleanup(Connection con, PreparedStatement ps) 
  {
    try {
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

