/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.sql.*;

/**
 *
 * @author marcus
 */
public class Admin
{
  private Connection conn;
  
  public Admin(Connection c)
  {
    this.conn = c;
  }
  
  public String getDBStatus()
  {
    // TODO
    return new String();
  }
  
  public int getNumRooms()
  {
    return aggregate("SELECT COUNT(*) FROM Rooms");
  }
  
  public int getNumReservations()
  {
    return aggregate("SELECT COUNT(*) FROM Reservations");
  }
  
  /**
   * Shortcut for executing queries and getting its results.
   * @param sql The SQL query.
   * @return ResultSet the result of the SQL query
   */
  private ResultSet query(String sql)
  {
    ResultSet results = null;
    
    try
    {
      Statement s = this.conn.createStatement();
      results = s.executeQuery(sql);
    }
    catch(SQLException e)
    {
      System.err.println("Error occured with SQL command.");
      System.exit(1);
    }
    
    return results;
  }
  
  private int aggregate(String sql)
  {
    int num = 0;
    ResultSet results = query(sql);
    
    try
    {
      results.next();
      num = results.getInt(1);
    }
    catch(SQLException e)
    {
      System.err.println("Error occured with SQL command.");
      System.exit(1);
    }
    
    return num;
  }
}
