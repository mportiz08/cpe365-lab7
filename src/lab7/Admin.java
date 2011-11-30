package lab7;

import java.sql.*;

/**
 * Admin -- implements the back end database work for AdminPanel
 * @author Marcus Ortiz
 */
public class Admin
{
  /** Reference to the SQL database connection. */
  private Connection conn;
  
  /**
   * Admin Constructor
   * @param c the reference to the SQL database connection
   */
  public Admin(Connection c)
  {
    this.conn = c;
  }
  
  /**
   * Returns the status of the database.
   * @return String either "full", "empty", or "no database"
   */
  public String getDBStatus()
  {
    // TODO
    return new String();
  }
  
  /**
   * Returns the number of rooms available in the database.
   * @return int number of rooms
   */
  public int getNumRooms()
  {
    return aggregate("SELECT COUNT(*) FROM Rooms");
  }
  
  /**
   * Returns the number of reservations available in the database.
   * @return int number of reservations
   */
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
  
  /**
   * Shortcut method for getting integer results from SQL statements
   * like COUNT, MAX, MIN, AVG, SUM, etc
   * @param sql the Aggregate SQL query
   * @return int the numeric result of the query
   */
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
