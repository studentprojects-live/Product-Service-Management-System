package webman;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.sql.*;

public class User
{

		  private String Name,companyId;
		  private String password, loginType, emailAddress;
		  public User(){}
				public boolean validUser(String name, String password, String userType)
				{
				Connection con = null;
				PreparedStatement ps = null;
				try {
						con = getConnection();
						ps  = con.prepareStatement("select * from Users where LoginName = ? and password = ? and LoginType=?");
						ps.setString(1, name);
						ps.setString(2, password);
						ps.setString(3, userType);
						ps.executeQuery();
						ResultSet rs = ps.getResultSet();
						if (rs.next())
						{
							return true;
						}
						else
						{
							String error = "User Package: User (" + name + ") not found";
							log(error);
							return false;
						}
				} 
				catch (SQLException sqe) 
				{
				log("SQLException:  " + sqe);
				} finally {cleanup(con, ps);}
				return false;
		}
				public String getUserType(String name)
				{
				Connection con = null;
				PreparedStatement ps = null;
				try {
						con = getConnection();
						ps  = con.prepareStatement("select LoginType from Users where LoginName = ?");
						ps.setString(1, name);
						ps.executeQuery();
						ResultSet rs = ps.getResultSet();
						if (rs.next())
						{
							return rs.getString(1);
						}
						else
						{
							String error = "User Package: User (" + name + ") not found";
							log(error);
							return null;
						}
				} 
				catch (SQLException sqe) 
				{
				log("SQLException:  " + sqe);
				} finally {cleanup(con, ps);}
				return null;
		}


  public void modifyUser(String companyId,String password,String loginType,String emailAddress,String Name)
  {
    Connection con = null;
    PreparedStatement ps = null;
	        log(Name+ "			username");
    try
    {   
        con = getConnection();
        ps = con.prepareStatement("update Users set CompanyId = ?, password = ?, LoginType = ?, emailAddress = ? where LoginName = ?");
		ps.setString(1, companyId);
		ps.setString(2, password);
		ps.setString(3, loginType);
		ps.setString(4, emailAddress);
		ps.setString(5, Name);

	   if (!(ps.executeUpdate() > 0)) {
        String error = "UserUpdate: User (" + Name + ") not updated";
        log(error);
      }
    } catch(SQLException sqe) {
      log("SQLException:  " + sqe);
    } finally {
      cleanup(con, ps);
    }
  }


public boolean createUser(String companyId, String name, String password, String loginType, String emailAddress)
  {
    this.companyId = companyId;
    this.Name = name;
    this.password = password;
    this.loginType = loginType;
    this.emailAddress = emailAddress;

    Connection con = null;
    PreparedStatement ps = null;
	if (validUser(name,password,loginType)==false)
	{
	 try {
      con = getConnection();
      ps = con.prepareStatement("insert into Users values (?, ?, ?, ?, ?)");

		ps.setString(2, companyId);
		ps.setString(1, Name);
		ps.setString(3, password);
		ps.setString(4, loginType);
		ps.setString(5, emailAddress);
	
      if (ps.executeUpdate() != 1)
	  {
        String error = "JDBC did not create any row";
        log(error);
      }
      return true;

    } catch (SQLException sqe) 
	{
        String error = "SQLException: " + sqe;
        log(error);
       }
	   finally {
      cleanup(con, ps);
    }
	
    }
	else
	{
     String error = "A User already exists in the database with Primary Key " + Name;
     log(error);
	 return false;
    }
	return false;
  }


  public void deleteUser(String name) 
  {

    Connection con = null;
    PreparedStatement ps = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("delete from Users where LoginName = ?");
      ps.setString(1, name);

      if (!(ps.executeUpdate() > 0)) 
	  {
        String error = "User (" + name  + ") not found";
        log(error);
      }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
    } finally {
      cleanup(con, ps);
    }
	
  }

  
  public Vector ShowUser(String loginName)
  {
    Connection con = null;
    PreparedStatement ps = null;
    Vector v=new Vector();
    try {
      con = getConnection();
      ps  = con.prepareStatement("select * from Users where LoginName = ?");
      ps.setString(1, loginName);
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();
      if (rs.next())
      {
		this.companyId = rs.getString(2);
		this.Name = rs.getString(1);
		this.password = rs.getString(3);
		this.loginType = rs.getString(4);
		this.emailAddress = rs.getString(5);
		v=getDetails();
      } else {
        String error = "Show User: User (" + loginName + ") not found";
        log(error);
       }
    } catch (SQLException sqe) {
      log("SQLException:  " + sqe);
    } finally {
      cleanup(con, ps);
    }
    log("User (" + loginName + ") found");
    return v;
  }

public Vector allUsers()
 {

    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
    String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select LoginName from Users");
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


public Vector ViewAllUsers(String cname)
 {
    log("FindAllUsers (" + companyId + ")");
    Connection con = null;
    PreparedStatement ps = null;
    Vector v = new Vector();
	      String pk = null;
    try {
      con = getConnection();
      ps = con.prepareStatement("select * from Users where companyId = ?");
      ps.setString(1, cname);	
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

 public void updateUser(String companyId, String name, String password, String loginType, String emailAddress)
{
    log("Updating User " + name);
    this.password = password;
    this.loginType = loginType;
    this.emailAddress = emailAddress;
  }

  public Vector getDetails()

  {
    Vector v = new Vector();
    v.addElement(companyId);
    v.addElement(Name);
    v.addElement(password);
    v.addElement(loginType);
    v.addElement(emailAddress);
    return v;
  }

		private Connection getConnection() throws SQLException
		{
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				java.sql.Connection myConnection=DriverManager.getConnection("jdbc:odbc:Webman");
				Statement myStatement=myConnection.createStatement();
				ResultSet rs=myStatement.executeQuery("Select * from users");
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

