package webman;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import java.sql.*;;

public class Part  {

  private String PartId, Name1,Name, Description, Status;
  private int qty, rol;
  public Part(){}

  public void validPart(String PartId) 
  {
    log("Part  called");

    Connection con = null;
    PreparedStatement ps = null;


    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from Parts where PartId = ?");
      ps.setString(1, PartId);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
        Name = rs.getString(2);
        Description = rs.getString(3);
        Status = rs.getString(6);
        qty = rs.getInt(5);
        rol = rs.getInt(4);
      }
      else
      {
        String error = "ejbLoad: Part  (" + PartId + ") not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
       
    } finally {
      cleanup(con, ps);
    }
  }

  public void modifyPart(String Name, String Description, String Status, int qty, int rol,String PartId) 
  {
    log("modify Part Part  called");

    Connection con = null;
    PreparedStatement ps = null;

    try
    {
      con = getConnection();
      ps = con.prepareStatement("update Parts set Name = ? , Description = ?, Status = ?, Quantity = ?, ReOrderLevel = ? where PartId = ?");
      ps.setString(1, Name);
      ps.setString(2, Description);
      ps.setString(3, Status);
      ps.setInt(4, qty);
      ps.setInt(5, rol);
      ps.setString(6, PartId);

      if (!(ps.executeUpdate() > 0)) {
        String error = "modifyPart: Part  (" + PartId + ") not updated";
        log(error);
         
      }
    } catch(SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }


  public boolean createPart(String PartId, String Name1, String Description, String Status, int qty, int rol)
    throws  Exception
  {
    log("Part .ejbCreate(" + PartId + ")");
    this.PartId = PartId;
    this.Name1 = Name1;
    this.Description = Description;
    this.Status = Status;
    this.qty = qty;
    this.rol = rol;

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
	  log("connection"+con);
      ps = con.prepareStatement("insert into Parts values (?, ?, ?, ?, ?, ?)");
      ps.setString(1, PartId);
      ps.setString(2, Name1);
      ps.setString(3, Description);
	  ps.setInt(4, rol);
	  ps.setInt(5, qty);
	  ps.setString(6, Status);
      

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
      String error = "A Part already exists in the database with Primary Key " + PartId;
    log(error);
 
    } 
	finally {
      cleanup(con, ps);
    }
	      return false;
  }

  public void deletePart(String PartId)
  {
    log("Remove Part  called");
  
    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = getConnection();
      ps = con.prepareStatement("delete from Parts where PartId = ?");
      ps.setString(1, PartId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "Part  (" + PartId + ") not found";
        log(error);
         
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
  }

  public Vector ShowPart(String pk)
     
  {
    log("ejbFindByPrimaryKey (" + pk + ")");

    Connection con = null;
    PreparedStatement ps = null;
	  Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from Parts where PartId = ?");
      ps.setString(1, pk);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();

      if (rs.next())
      {
        this.Name = rs.getString(2);
        this.Description = rs.getString(3);
        this.Status = rs.getString(6);
        this.qty = rs.getInt(5);
        this.rol = rs.getInt(4);
        this.PartId=rs.getString(1);
		v=getDetails();
	  } 

	else 
	{
        String error = "ejbFindByPrimaryKey: Part  (" + pk + ") not found";
        log(error);
         
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
 
    } finally {
      cleanup(con, ps);
    }
    log("ejbFindByPrimaryKey (" + pk + ") found");
    return v;
  }

public Vector allParts()
 {
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select PartId from Parts");
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

public void updatePart(String Name, String Description, String Status, int qty, int rol)
{
 log("Part .updatePart(" + Name + ")");
    this.Name = Name;
    this.Description = Description;
    this.Status = Status;
    this.qty = qty;
    this.rol = rol;
}

 public void updatePartStock(int qty)
 {
    log("Updating Part stock" + PartId);
	this.qty = this.qty + qty;
 }

  public Vector getDetails()
  {
    Vector v = new Vector();
    v.addElement(PartId);
    v.addElement(Name);
    v.addElement(Description);
    v.addElement(Status);
    v.addElement(Integer.toString(qty));
    v.addElement(Integer.toString(rol));
    return v;
  }

  	private Connection getConnection() throws SQLException
		{
			log("starts");
		try {
				
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from Parts");
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

  // Return a String that contains this  s id

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