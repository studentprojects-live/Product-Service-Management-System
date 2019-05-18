package webman;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import java.sql.*;

public class Product{

  private String ProductId, Name1,Name, Description, Status;
  private double Price;
  public Product(){}
  //HttpSession hs=req.getSession(false);
  public void validProduct(String ProductId)
  {
    log("ValidProduct of Product  called");

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from Products where ProductId = ?");
      ps.setString(1, ProductId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        Name = rs.getString(1);
        Description = rs.getString(2);
        Price = rs.getDouble(3);
        Status = rs.getString(4);
      }
      else
      {
        String error = "Product  (" + ProductId + ") not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
       
    } finally {
      cleanup(con, ps);
    }
  }

  public void modifyProduct(String ProductId, String Name, String Description, double Price, String Status)
  {
    log("modiyProduct of Product  called");

    Connection con = null;
    PreparedStatement ps = null;

    try
    {
      con = getConnection();
      ps = con.prepareStatement("update Products set Name = ? , Description = ?, Price = ?, Status = ? where ProductId = ?");
      ps.setString(1, Name);
      ps.setString(2, Description);
      ps.setDouble(3, Price);
      ps.setString(4, Status);
      ps.setString(5, ProductId);
      if (!(ps.executeUpdate() > 0))
	{
        String error = "modify Product  (" + ProductId + ") not updated";
        log(error);
         
      }
    } catch(SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
 }

	public boolean createProduct(String ProductId, String Name1, String Description, double Price, String Status)
    	throws  Exception
  	{
    		log("Product create( id = ProductId" + ", " +"name"+Name1+ "initial price = £ " + Price + ")");
			this.ProductId = ProductId;
		    this.Name1 = Name1;
    		this.Description = Description;
    		this.Price = Price;
    		this.Status = Status;

    		Connection con = null;
    		PreparedStatement ps = null;

    		try
		{
      		con = getConnection();
			log("connection"+con);
      		ps = con.prepareStatement("insert into Products values (?, ?, ?, ?, ?)");
      		ps.setString(1, ProductId);
      		ps.setString(2, Name1);
      		ps.setString(3, Description);
      		ps.setDouble(4, Price);
      		ps.setString(5, Status);
			
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
      		String error = "A Product already exists in the database with Primary Key " + ProductId;
      		log(error);
    		 
    	} 
		finally 
		{
		      cleanup(con, ps);
    		}
			      		return false;
  	}


  	public void deleteProduct(String ProductId) 
	{
		log("Remove product() of Product  called");
    		Connection con = null;
    		PreparedStatement ps = null;

    		try 
		{
      		con = getConnection();

      		ps = con.prepareStatement("delete from Products where ProductId = ?");
      		ps.setString(1, ProductId);
      		if (!(ps.executeUpdate() > 0)) 
			{
        			String error = "Product  (" + ProductId + " not found";
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

	public Vector ShowProduct(String pk)
    		 
  	{
    		log("Display of Product  (" + pk + ")");
    		Connection con = null;
    		PreparedStatement ps = null;
			Vector v=new Vector();
		try 
		{
      		con = getConnection();
      		ps  = con.prepareStatement("select * from Products where ProductId = ?");
      		ps.setString(1, pk);
      		ps.executeQuery();
      		ResultSet rs = ps.getResultSet();
      		if (rs.next())
      		{
			        this.ProductId=rs.getString(1);
        			this.Name = rs.getString(2);
        			this.Description = rs.getString(3);
        			this.Price = rs.getDouble(4);
        			this.Status = rs.getString(5);
					v=getDetails();
      		} 
			else 
			{
        			String error = "Display of product  (" + pk + ") not found";
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

    		log("Display of Product  (" + pk + ") found");
    		return v;
  	}

 public Vector allProducts()
 {
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select ProductId from Products");
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

	
	
	public Enumeration FindBigProducts(double priceGreaterThan) 
	{
    		log("FindBigProducts (Price > " + priceGreaterThan + ")");
    		Connection con = null;
    		PreparedStatement ps = null;
    		Vector v = new Vector();
      		String pk;
    		try 
		{
      		con = getConnection();
      		ps = con.prepareStatement("select ProductId from Products where Price > ?");
      		ps.setDouble(1, priceGreaterThan);
      		ps.executeQuery();
      		ResultSet rs = ps.getResultSet();
  

		      while (rs.next()) 
			{
        			pk = rs.getString(1);
			      v.addElement(pk);
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

  	public double updateProduct(String Name, String Description, double Price, String Status) 
	{
    		log("Updating Product Price changed to £" + Price + " For '" + ProductId + "'");
	
		this.Name = Name;
		this.Description = Description;
		this.Price = Price;
		this.Status = Status;

		Connection con = null;
    		PreparedStatement ps = null;

    		try
    		{
      		con = getConnection();
      		ps = con.prepareStatement("update Products set Name = ? , Description = ?, Price = ?, Status = ? where ProductId = ?");
      		ps.setString(1, Name);
      		ps.setString(2, Description);
      		ps.setDouble(3, Price);
      		ps.setString(4, Status);
      		ps.setString(5, ProductId);
      		if (!(ps.executeUpdate() > 0))
			{
        			String error = " Store: Product  (" + ProductId + ") not updated";
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
    		return Price;
  	}

	public Vector getDetails()
    		 
  	{
		log("getDetails() of Product  called.");
    		Vector v = new Vector();
    		v.addElement(ProductId);
    		v.addElement(Name);
    		v.addElement(Description);
    		v.addElement(Double.toString(Price));
    		v.addElement(Status);
    		return v;
  	}


  	public Enumeration FindAllProductNames(Vector productIds)
  	{
		log("In findAllProductNames() of Product  ~~~~~~~~~~~");

   		Vector v = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		try
    		{
       		String queryStr = "";
       		for(int x = 0; x < productIds.size(); x++)
               	queryStr  += "'" + productIds.elementAt(x) + "',";
       		queryStr = queryStr.substring(0, (queryStr.length()-1));

	  		con = getConnection();
			
	  		ps = con.prepareStatement("select * from Products where productId in (" + queryStr + ") order by ProductId"); //+++ check

	  		try
        		{
	    			ResultSet rs = ps.executeQuery();

          			while(rs.next())
	    			{
          				String pId = rs.getString(1);
          				v.addElement(pId);
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
				log("In findAllProductNames() of Product  ~~ BEFORE returning ");
     		return v.elements();
  	}


private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from Products");
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