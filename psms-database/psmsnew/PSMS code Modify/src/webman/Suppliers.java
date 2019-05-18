package webman;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.sql.*;

public class Suppliers {

  private String SupplierId, Name, Address, Status,Email;
  private double CreditLimit;
  public Suppliers (){}
  public void validSupplier(String SupplierId)
  {
    log("Supplier: (" + SupplierId +  ")");
    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps  = con.prepareStatement("select Name, Address, CreditLimit, Status, Email from Suppliers where SupplierId = ?");
      ps.setString(1, SupplierId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        Name = rs.getString(1);
        Address = rs.getString(2);
        CreditLimit = rs.getDouble(3);
        Status = rs.getString(4);
        Email = rs.getString(5);
      }
      else
      {
        String error = "Supplier: Supplier  (" + SupplierId + ") not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
       
    } finally {
      cleanup(con, ps);
    }
  }


 public void modifySuppliers(String Name, String Address, double CreditLimit, String Status,String Email,String SupplierId)
  {
    log("DEALER (" + SupplierId + ")");

    Connection con = null;
    PreparedStatement ps = null;

    try
    {
    	
    	 System.out.println(SupplierId);
      con = getConnection();
      ps = con.prepareStatement("update  Suppliers set Name = ? , Address = ?, CreditLimit = ?, Status = ? , Email = ? where SupplierId = ?");
      ps.setString(1, Name);
      ps.setString(2, Address);
      ps.setDouble(3, CreditLimit);
      ps.setString(4, Status);
      ps.setString(5, Email);
      ps.setString(6, SupplierId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "Supplier: Supplier  (" + SupplierId + ") not updated";
        log(error);
         
      }
    } catch(SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }


  public boolean createSuppliers(String SupplierId, String Name, String Address, double CreditLimit, String Status, String Email)   throws  Exception
  {
    log("Dealer Create( id = DealerId" + ", " + "initial CreditLimit = £ " + CreditLimit + ")");
    this.SupplierId = SupplierId;
    this.Name = Name;
    this.Address = Address;
    this.CreditLimit = CreditLimit;
    this.Status = Status;
    this.Email=Email;

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      System.out.println(SupplierId);
      ps = con.prepareStatement("insert into Suppliers (SupplierId, Name, Address, CreditLimit, Status, Email)  values (?, ?, ?, ?, ?, ?)");
      ps.setString(1, SupplierId);
      ps.setString(2, Name);
      ps.setString(3, Address);
      ps.setDouble(4, CreditLimit);
      ps.setString(5, Status);
      ps.setString(6, Email);

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
      String error = "A Supplier already exists in the database with Primary Key " + SupplierId;
      log(error);
       
    }
	finally {
      cleanup(con, ps);
    }
	return false;
  }

  public void deleteSuppliers(String SupplierId) 
  {
    log("Supplier Remove (" + SupplierId + ")");

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("delete from Suppliers where SupplierId = ?");
      ps.setString(1, SupplierId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "Supplier  (" + SupplierId + " not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }

  public Vector ShowSuppliers(String pk)
     
  {
    log("ShowSuppliers(" + pk + ")");

    Connection con = null;
    PreparedStatement ps = null;
	Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from Suppliers where SupplierId = ?");
      ps.setString(1, pk);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
	   this.SupplierId=rs.getString(1);
       this.Name = rs.getString(2);
       this.Address = rs.getString(3);
       this.CreditLimit = rs.getDouble(4);
       this.Status = rs.getString(5);
       this.Email = rs.getString(6);
       v=getDetails();
      } else 
    	{
        String error = "ShowSuppliers  (" + pk + ") not found";
        log(error);
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }

    log("getSupplier (" + pk + ") found");
    return v;
  }


public Vector allSuppliers()
 {
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select SupplierId from Suppliers");
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

public Vector allSuppliers(String sid)
 {
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select SupplierId from Suppliers where SupplierId='"+sid+"'");
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



  public Vector allbigSuppliers(double CreditLimitGreaterThan) 
  {
    log("allbigSuppliers (CreditLimit > " + CreditLimitGreaterThan + ")");
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
      String pk;
    try {
      con = getConnection();
      ps = con.prepareStatement("select SupplierId from Suppliers where CreditLimit > ?");
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

  public double updateSupplier(String Name, String Address, double CreditLimit, String Status, String Email) {
    log("Updating Dealer Credit Limit changed to £" + CreditLimit + " For '" + SupplierId + "'");
    this.Name = Name;
    this.Address = Address;
    this.CreditLimit = CreditLimit;
    this.Status = Status;
    this.Email=Email;
    return CreditLimit;
  }

  public Vector getDetails()
     
  {
    Vector v = new Vector();
    v.addElement(SupplierId);
    v.addElement(Name);
    v.addElement(Address);
    v.addElement(Double.toString(CreditLimit));
    v.addElement(Status);
    v.addElement(Email);
    return v;
  }


		private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from Suppliers");
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

