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


public class ProductTrans
{

	private String DealerId, ProductId, Comments;
	private int Quantity;
	private Date TDate; 
    public ProductTrans(){}
  	public void validProductTrans(String ProductId,String DealerId) 
	{
    		log(" Load() of ProductTrans  called");

    		Connection con = null;
    		PreparedStatement ps = null;


    		try 
		{
      		con = getConnection();
      		ps  = con.prepareStatement("select Quantity, Comments from ProductTrans where DealerId = ? and ProductId = ? and TDate = ?");
     		ps.setString(1, DealerId);
      		ps.setString(2, ProductId);
      		ps.setDate(3, new java.sql.Date(TDate.getTime()));

      		ps.executeQuery();
		      ResultSet rs = ps.getResultSet();
      		if (rs.next())
      		{
        			Quantity = rs.getInt(1);
        			Comments = rs.getString(2);
      		}
      		else
      		{
        			String error = " Load: ProductTrans  (" + ProductId+DealerId + ") not found";
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

  	public void  modifyProductTrans(String DealerId, String ProductId, Date TDate, int Quantity, String Comments)
  	{
    		log(" Store () of ProductTrans  called");

    		Connection con = null;
    		PreparedStatement ps = null;


    		try
    		{
      		con = getConnection();
      		ps = con.prepareStatement("update ProductTrans set Quantity = ? , Comments = ? where DealerId = ? and ProductId = ? and TDate = ?");
      		ps.setInt(1, Quantity);
      		ps.setString(2, Comments);
      		ps.setString(3, DealerId);
      		ps.setString(4, ProductId);
      		ps.setDate(5, new java.sql.Date(TDate.getTime()));

      		if (!(ps.executeUpdate() > 0)) 
			{
        			String error = " Store: ProductTrans  (" + ProductId + ") not updated";
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

  	public boolean createProdctTRans(String DealerId, String ProductId, Date TDate, int Quantity, String Comments)
    		throws Exception
  	{
    		log("ProductTrans . Create( id = ProductId" + ", " + "initial Quantity = £ " + Quantity + ")");
    		this.DealerId = DealerId;
    		this.ProductId = ProductId;
    		this.TDate = TDate;
    		this.Quantity = Quantity;
    		this.Comments = Comments;

    		Connection con = null;
	    	PreparedStatement ps = null;

    		try 
		{
      		con = getConnection();
      		ps = con.prepareStatement("insert into ProductTrans (DealerId, ProductId, TDate, Quantity, Comments)  values (?, ?, ?, ?, ?)");
      		ps.setString(1, DealerId);
      		ps.setString(2, ProductId);
      		ps.setDate(3, new java.sql.Date(TDate.getTime()));
      		ps.setInt(4, Quantity);
      		ps.setString(5, Comments);

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
      		String error = "A ProductTrans already exists in the database with Primary Key " + ProductId;
      		log(error);
      		 
    		} 
		finally 
		{
      		cleanup(con, ps);
    		}
  	      		return false;
	}


	public void  deleteProductTrans(String ProductId,String DealerId,Date TDate) 
	{
    		log(" Remove() of ProductTrans  called");
    		Connection con = null;
    		PreparedStatement ps = null;
		try 
		{
      		con = getConnection();
      		ps = con.prepareStatement("delete from ProductTrans where ProductId = ? and DealerId = ? and TDate = ?");
      		ps.setString(1, ProductId);
      		ps.setString(2, DealerId);
      		ps.setDate(3, new java.sql.Date(TDate.getTime()));

      		if (!(ps.executeUpdate() > 0)) 
			{
        			String error = "ProductTrans (" + ProductId + ") not found";
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

	public Vector showProductTrans(String ProdcutId,String DealerId,Date TDate)
    	 
  	{
    		log(" FindByPrimaryKey (" + ProductId + ")");

    		Connection con = null;
    		PreparedStatement ps = null;
			Vector v=new Vector();

    		try 
		{
      		con = getConnection();
	      	ps  = con.prepareStatement("select  DealerId, ProductId, TDate, Quantity, Comments from ProductTrans where  ProductId = ? and DealerId = ? and TDate = ?");
      		ps.setString(1, ProductId);
      		ps.setString(2, DealerId);
    		ps.setDate(3, new java.sql.Date(TDate.getTime()));

      		ps.executeQuery();
      		ResultSet rs = ps.getResultSet();
	      	if (rs.next())
      		{
        			this.DealerId = rs.getString(1);
        			this.ProductId = rs.getString(2);
	        		this.TDate = rs.getDate(3);
      	  		    this.Quantity = rs.getInt(4);
        			this.Comments = rs.getString(5);
					v=getDetails();
      		} 
			else 
			{
        			String error = " FindByPrimaryKey: ProductTrans  (" + ProductId + ") not found";
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

    		log(" FindByPrimaryKey (" + ProductId + ") found");
	    	return v;
  	}


   	public Vector  AllProductTrans(String dealerId,String ProductId,Date TDate)
	{
		log("In  FindAllProducts() of ProductTrans  ~~~~~~~~~~~");


    		Vector v = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		try
 		{
			con = getConnection();
		  	ps = con.prepareStatement("select  DealerId, ProductId, TDate, Quantity, Comments from ProductTrans where  ProductId = ? and DealerId = ? and TDate = ?");
		  	ps.setString(1, dealerId);
		  	ps.setString(2, ProductId);
			ps.setDate(3, new java.sql.Date(TDate.getTime()));
		  	try
      		{
		    		ResultSet rs = ps.executeQuery();
					int xx = 1;
    				while(rs.next())
	     			{
					ProductId = rs.getString(2);
					DealerId = rs.getString(1);
      				v.addElement(DealerId);
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

		log("BEFORE return in findAllProductTrans() ***");

		return v;
	}



	public Enumeration  FindBigProductTrans(int quantityGreaterThan) 
	{
		log(" FindBigProductTrans (Quantity > " + quantityGreaterThan + ")");
    		Connection con = null;
		PreparedStatement ps = null;
      		Vector v = new Vector();
			String pk;

    		try 
		{
      		con = getConnection();
      		ps = con.prepareStatement("select * from ProductTrans where Quantity > ?");
      		ps.setInt(1, quantityGreaterThan);
      		ps.executeQuery();
      		ResultSet rs = ps.getResultSet();
      		while (rs.next()) 
			{
        			pk = rs.getString(1)+ rs.getString(2)+ rs.getDate(3);
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

  	public int updateProductTrans(String DealerId, String ProductId, Date TDate, int Quantity, String Comments) 
	{
    		log("Updating ProductTrans Quantity changed to £" + Quantity + " For '" + ProductId + "'");
    		this.DealerId = DealerId;
    		this.ProductId = ProductId;
    		this.TDate = TDate;
    		this.Quantity = Quantity;
    		this.Comments = Comments;

    		Connection con = null;
    		PreparedStatement ps = null;

    		try
    		{
      		con = getConnection();
      		ps = con.prepareStatement("update ProductTrans set Quantity = ? , Comments = ? where DealerId = ? and ProductId = ? and TDate = ?");
      		ps.setInt(1, Quantity);
      		ps.setString(2, Comments);
      		ps.setString(3, DealerId);
      		ps.setString(4, ProductId);
      		ps.setDate(5, new java.sql.Date(TDate.getTime()));

      		if (!(ps.executeUpdate() > 0)) 
			{
        			String error = "ProductTrans  not updated";
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
    		Vector v = new Vector();
    		v.addElement(DealerId);
    		v.addElement(ProductId);
    		v.addElement(TDate.toString());
    		v.addElement(Integer.toString(Quantity));
    		v.addElement(Comments);
    		return v;
  	}

	public int getQuantity() 
	{
    		return Quantity;
  	}

private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from ProductTrans");
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