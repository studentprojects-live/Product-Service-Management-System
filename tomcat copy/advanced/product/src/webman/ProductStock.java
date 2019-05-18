package webman;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Date;
import java.sql.*;

public class ProductStock {

private String DealerId, ProductId;
private int Quantity, Reorderlevel;
public ProductStock()
{}
  	public void validProductStock(String DealerId,String ProductId) 
	{
    		log("validation of ProductStock  called");

    		Connection con = null;
    		PreparedStatement ps = null;
       		try 
		{
      		con = getConnection();
      		ps  = con.prepareStatement("select Quantity, Reorderlevel from ProductStock where DealerId = ? and ProductId = ?");
      		ps.setString(1, DealerId);
      		ps.setString(2, ProductId);
      		ps.executeQuery();
            ResultSet rs = ps.getResultSet();
      		if (rs.next())
      		{
        			Quantity = rs.getInt(1);
        			Reorderlevel = rs.getInt(2);
      		}
      		else
      		{
        			String error = "validation ProductStock (" +DealerId+ProductId + ") not found";
        			log(error);
        			 
      		}
    		} 
		catch (SQLException sqe) 
		{
		      log("SQLException:  " + sqe);
		       
		} 
		finally 
		{
      		cleanup(con, ps);
    		}
  	}

  	public void modifyProductStock(String ProductId,String DelaerId,int Quantity,int Reorderlevel)
  	{
    		log("modification of ProductStock  called");
    		
    		 System.out.println(ProductId);

    		Connection con = null;
    		PreparedStatement ps = null;


    		try
    		{
      		con = getConnection();
			System.out.println("In ProductStock.java After connection, con created");
			ps = con.prepareStatement("update ProductStock set Quantity = ?, Reoderlevel = ? where DealerId = ? and ProductId = ?");
			
      		ps.setInt(1, Quantity);
      		ps.setInt(2, Reorderlevel);
      		ps.setString(3,DealerId);
      		ps.setString(4, ProductId);
      		
      		System.out.println("In ProductStock.java After Prepared statement");
			System.out.println("In ProductStock.java before if");
			int pscount = ps.executeUpdate();
			System.out.println("In ProductStock.java count value is " +pscount);

      		if (!(ps.executeUpdate() > 0)) 
			{
				System.out.println("In ProductStock.java after if");
        			String error = "modify: ProductStock (" + ProductId + ") not updated";
        			log(error);
        			 
      		}
    		} 
		catch(SQLException sqe) 
		{
      		log("SQLException:  " + sqe);
      		 
    		} 
		finally 
		{
      		cleanup(con, ps);
    		}
  	}


  	public boolean createProductStock(String DealerId, String ProductId, int Quantity, int Reorderlevel)
    		throws  Exception
  	{
    		log("ProductStock .Create( id = " + ProductId + ", " + "initial Quantity = £ " + Quantity + ")");
    		this.DealerId = DealerId;
    		this.ProductId = ProductId;
    		this.Quantity = Quantity;
    		this.Reorderlevel = Reorderlevel;

    		Connection con = null;
    		PreparedStatement ps = null;

    		try 
		{
      		con = getConnection();
      		ps = con.prepareStatement("insert into ProductStock values (?, ?, ?, ?)");

      		ps.setString(1, DealerId);
      		ps.setString(2, ProductId);
      		ps.setInt(3, Quantity);
      		ps.setInt(4, Reorderlevel);
      		if (ps.executeUpdate() != 1) 
			{
        			String error = "JDBC did not create any row";
        			log(error);
        			 
      		}
		return true;
    		} 
		catch (SQLException sqe) 
		{
      		try 
			{
      		} 
			catch(Exception onfe) 
			{
        			String error = "SQLException: " + sqe;
        			log(error);
        			 
      		}
      		String error = "A ProductStock already exists in the database with Primary Key ";
      		log(error);
      		 
    		} 
		finally 
		{
	      	cleanup(con, ps);
    		}
					return false;
  	}

  	public void deleteProductStock(String ProductId,String DealerId) 
	{
    		log("Remove () of ProductStock called");
    	
		    Connection con = null;
    		PreparedStatement ps = null;

    		try 
		{
      		con = getConnection();

      		ps = con.prepareStatement("delete from ProductStock where DealerId = ? and ProductId = ?");
      		ps.setString(1, DealerId);
      		ps.setString(2, ProductId);
      		if (!(ps.executeUpdate() > 0)) 
			{
        			String error = "ProductStock (" + DealerId + ProductId + " not found";
        			log(error);
        			 
      		}
    		} 
		catch (SQLException sqe) 
		{
      		log("SQLException:  " + sqe);
      		 
    		} 
		finally 
		{
		      cleanup(con, ps);
    		}
  	}




  	public Vector ShowProductStock(String DealerId,String ProductId)
  	{
    log(" FindByPrimaryKey Product Stock(" + ProductId+DealerId + ")");
    Connection con = null;
    PreparedStatement ps = null;
	Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from ProductStock where ProductId = ? and DealerId = ?");
      ps.setString(1, ProductId);
      ps.setString(2, DealerId);
  //    ps.setInt(3,0);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        this.DealerId = rs.getString(1);
        this.ProductId = rs.getString(2);
        this.Quantity = rs.getInt(3);
		this.Reorderlevel = rs.getInt(4);
		v=getDetails();
      } else {
        String error = " FindByPrimaryKey:ProductStock  (" + ProductId+DealerId + ") not found";
        log(error);
         
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }

    log(" FindByPrimaryKey (" + ProductId+DealerId + ") found");
    return v;
  
  	}

public Vector ShowProductStock(String DealerId)
  	{
  
    log(" FindByPrimaryKey Product Stock(" + DealerId + ")");
    Connection con = null;
    PreparedStatement ps = null;
	Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select DealerId,ProductId,Quantity,ReorderLevel from ProductStock where  DealerId = ?");
//      ps.setString(1, ProductId);
      ps.setString(1, DealerId);
  //    ps.setInt(3,0);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        this.DealerId = rs.getString(1);
        this.ProductId = rs.getString(2);
        this.Quantity = rs.getInt(3);
		this.Reorderlevel = rs.getInt(4);
		v=getDetails();
      } else {
        String error = " FindByPrimaryKey:ProductStock  (" +DealerId + ") not found";
        log(error);
         
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }

    log(" FindByPrimaryKey (" + ProductId+DealerId + ") found");
    return v;
  
  	}

	public Enumeration  FindBigProductStock(int quantityGreaterThan) 
	{
    		log(" FindBigProductStock (Quantity > " + quantityGreaterThan + ")");
    		Connection con = null;
    		PreparedStatement ps = null;
		Vector v = new Vector();

		String pdid;
    		try 
		{
      		con = getConnection();
      		ps = con.prepareStatement("select ProductId, DealerId from ProductStock where Quantity > ?");
      		ps.setInt(1, quantityGreaterThan);
      		ResultSet rs = ps.executeQuery();

      		while (rs.next()) 
			{
				ProductId = rs.getString(1);
				DealerId = rs.getString(2);
       			v.addElement(ProductId+DealerId);
      		}
      		return v.elements();
    		} 
		catch (SQLException sqe) 
		{
      		log("SQLException: " + sqe);
      		 
    		} 
		finally 
		{
      		cleanup(con, ps);
    		}
			      		return v.elements();
  	}

	public int updateProductStock(String DealerId, String ProductId, int Quantity) 
	{
    		log("Updating ProductStock Quantity changed to £" + Quantity + " For '" + ProductId + "'");
    		this.DealerId = DealerId;
    		this.ProductId = ProductId;
    		this.Quantity = this.Quantity + Quantity;

    		Connection con = null;
    		PreparedStatement ps = null;

    		try
    		{
      		con = getConnection();
      		ps = con.prepareStatement("update productstock set quantity = ? where dealerid = ? and productid = ?");
      		ps.setInt(1, Quantity);
      		ps.setString(2, DealerId);
      		ps.setString(3, ProductId);

      		if (!(ps.executeUpdate() > 0)) 
			{
        			String error = "ProductStock not updated";
        			log(error);
        			 
      		}
    		} 
		catch(SQLException sqe) 
		{
      		log("SQLException:  " + sqe);
      		 
    		} 
		finally 
		{
      		cleanup(con, ps);
    		}
  		return Quantity;
  	}

  	public Vector getDetails()
    		 
  	{
    		log("getDetails() of ProductStock  called");
		Vector v = new Vector();
    		v.addElement(DealerId);
    		v.addElement(ProductId);
    		v.addElement(Integer.toString(Quantity));
    		v.addElement(Integer.toString(Reorderlevel));
    		return v;
  	}

  	public int getQuantity() 
	{
    		return Quantity;
  	}


   	public Vector  allProductStock()
	{
		log("In  FindAllProducts() of ProductStock  ~~~~~~~~~~~");
   		Vector v = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		try
 		{
			con = getConnection();
		  	ps = con.prepareStatement("select DealerId,ProductId from ProductStock order by DealerId");
		  	try
      		{
		    		ResultSet rs = ps.executeQuery();
					int xx = 1;
    				while(rs.next())
	     			{
					   DealerId=rs.getString(1);
	    			   v.addElement(DealerId);
					   ProductId=rs.getString(2);
	    			   v.addElement(ProductId);
		System.out.println("product stock" + DealerId + " dd " + ProductId);
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

		log("BEFORE return in findAllProducts() ***");

		return v;
	}

private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from ProductStock");
				return myConnection;
		} catch(Exception ne) 
		{
		log("UNABLE to get a connection from demoPool!");
		log("Please make sure that you have setup the connection pool properly");
		} 
		return null;
		}

	private void log(String s) 
	{
		  System.out.println(s);
	}


	private void cleanup(Connection con, PreparedStatement ps) 
	{
		try 
		{
			if (ps != null) ps.close();
		} 
		catch (Exception e) 
		{
			log("Error closing PreparedStatement: "+e);
			 
		}

		try 
		{
			if (con != null) con.close();
		} 
		catch (Exception e) 
		{
			log("Error closing Connection: " + e);
			 
		}
	}
}
