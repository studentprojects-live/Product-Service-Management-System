package webman;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;


import java.sql.*;

public class BillOfMaterials
{

  private String ProductId, PartId;
  private int NoRequired;
  public BillOfMaterials(){} 
  public void valiidBOFM(String ProdctId,String PartId) 
  {
    log("VALIDATION of BillOfMaterials called ");

    Connection con = null;
    PreparedStatement ps = null;
    

    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from BillOfMaterials where ProductId = ? and PartId = ?");
      ps.setString(1, ProductId);
      ps.setString(2, PartId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        ProductId = rs.getString(1);
        PartId = rs.getString(2);
        NoRequired = rs.getInt(3);
      }
      else
      {
        String error = "valid of BillOfMaterials  (" + ProductId + ") not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
       
    } finally {
      cleanup(con, ps);
    }
  }

  public void modifyBOFM(String productId, String partId, int noReq) 
  {
    log("modification of BillOfMaterials called");

    Connection con = null;
    PreparedStatement ps = null;

    try
    {
      con = getConnection();
System.out.println("aaaa" + productId + partId + noReq);
      ps = con.prepareStatement("update BillOfMaterials set NoRequired = ? where ProductId = ? and PartId = ?");
      ps.setInt(1, noReq);
      ps.setString(2, productId);
      ps.setString(3, partId);
      if (!(ps.executeUpdate() > 0)) {
        String error = " Store: BillOfMaterials  ('" + productId + "') not updated";
        log(error);
         
      }
    } catch(SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }


  public boolean createBOFM(String productId, String partId, int noReq)
    throws  Exception
  {
    log("BillOfMaterials .Create(" + productId + ") called");
    this.ProductId = productId;
    this.PartId = partId;
    this.NoRequired = noReq;

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("insert into BillOfMaterials values (?, ?, ?)");
      ps.setString(1, ProductId);
      ps.setString(2, PartId);
      ps.setInt(3, NoRequired );

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
      String error = "A BillOfMaterials already exists in the database with Primary Key " + ProductId+ PartId;
      log(error);
       
    } finally {
      cleanup(con, ps);
    }
	      return false;
  }

  public void delBOFM(String ProductId,String PartId) {
    log("Remove() of BillOfMaterials called");

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      	
      ps = con.prepareStatement("delete from BillOfMaterials where ProductId = ? and PartId=?");
      ps.setString(1, ProductId);
	        ps.setString(2, PartId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "BillOfMaterials  (" + ProductId + ") not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }

  public Vector ShowBOM(String ProductId,String PartId)
  {
    log(" FindByPrimaryKey (" + ProductId+PartId + ")");

    Connection con = null;
    PreparedStatement ps = null;
	Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select ProductId, PartId, NoRequired from BillOfMaterials where ProductId = ? and PartId = ?");
      ps.setString(1, ProductId);
      ps.setString(2, PartId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        this.ProductId = rs.getString(1);
        this.PartId = rs.getString(2);
        this.NoRequired = rs.getInt(3);
		v=getDetails();
      } else {
        String error = " FindByPrimaryKey: BillOfMaterials  (" + ProductId+PartId + ") not found";
        log(error);
         
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }

    log(" FindByPrimaryKey (" + ProductId+PartId + ") found");
    return v;
  }

public Vector ShowBOFM(String ProductId)
  {
    log(" FindByPrimaryKey (" + ProductId+ ")");

    Connection con = null;
    PreparedStatement ps = null;
	Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select ProductId, PartId, NoRequired from BillOfMaterials where ProductId = ? ");
      ps.setString(1, ProductId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        this.ProductId = rs.getString(1);
        this.PartId = rs.getString(2);
        this.NoRequired = rs.getInt(3);
		v=getDetails();
      } else {
        String error = " FindByPrimaryKey: BillOfMaterials  (" + ProductId + ") not found";
        log(error);
         
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }

    log(" FindByPrimaryKey (" + ProductId+ ") found");
    return v;
  }


	public Vector  allBOFM()
	{
		log("In  FindAllBill of Material   ~~~~~~~~~~~");
   		Vector v = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		try
 		{
			con = getConnection();
		  	ps = con.prepareStatement("select ProductId from BillOfMaterials order by ProductId");
		  	try
      		{
		    		ResultSet rs = ps.executeQuery();
					int xx = 1;
    				while(rs.next())
	     			{
					   ProductId=rs.getString(1);
	    			   v.addElement(ProductId);
       			}
      		}
      		catch (SQLException sqe)
      		{
        			log("SQLException:  " + sqe);
        			throw sqe;
			}
		}
		catch(SQLException sqe)
		{
			log("SQLException:  " + sqe);
		}

		log("BEFORE return in findAll Bill of Materials ***");

		return v;
	}




  public void updateBillOfMaterials(String productId, String partId, int noReq)
 {
    log("Updating BillOfMaterials ");
    this.ProductId = productId;
    this.PartId = partId;
    this.NoRequired = noReq;
  }
  public Vector getDetails()
     
  {
    Vector v = new Vector();
    v.addElement(ProductId);
    v.addElement(PartId);
    v.addElement(Integer.toString(NoRequired));
    return v;
  }

  public int getNoRequired()
  {
    return NoRequired;
  }

  private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from BillOfMaterials");
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


  private void cleanup(Connection con, PreparedStatement ps) {
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