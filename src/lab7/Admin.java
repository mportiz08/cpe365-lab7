package lab7;

import java.sql.*;
import java.util.*;

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
    String status = null;
    
    if(this.getNumRooms() == 0 && this.getNumReservations() == 0)
    {
      status = "empty";
    }
    else
    {
      status = "full";
    }
    
    return status;
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
  
  public Object[][] getRooms()
  {
    ResultSet rows = query("SELECT * FROM Rooms");
    ArrayList<Object[]> rooms = new ArrayList<Object[]>();
    
    try
    {
      while(rows.next())
      {
        String id = rows.getString("Id");
        String name = rows.getString("Name");
        Integer beds = rows.getInt("Beds");
        String bedType = rows.getString("BedType");
        Integer maxOccupancy = rows.getInt("MaxOccupancy");
        Integer basePrice = rows.getInt("BasePrice");
        String decor = rows.getString("Decor");
        Object[] room = {id, name, beds, bedType, maxOccupancy, basePrice, decor};
        rooms.add(room);
      }
    }
    catch(SQLException e)
    {
      System.err.println("Error retrieving Room records from database.");
      System.exit(1);
    }
    
    return rooms.toArray(new Object[rooms.size()][rooms.size()]);
  }
  
  public Object[][] getReservations()
  {
    ResultSet rows = query("SELECT * FROM Reservations");
    ArrayList<Object[]> reservations = new ArrayList<Object[]>();
    
    try
    {
      while(rows.next())
      {
        Integer code = rows.getInt("Code");
        String room = rows.getString("Room");
        java.sql.Date checkIn = rows.getDate("CheckIn");
        java.sql.Date checkOut = rows.getDate("CheckOut");
        Double rate = rows.getDouble("Rate");
        String lastName = rows.getString("LastName");
        String firstName = rows.getString("FirstName");
        Integer adults = rows.getInt("Adults");
        Integer kids = rows.getInt("Kids");
        Object[] reservation = {code, room, checkIn, checkOut, rate, lastName,
                                firstName, adults, kids};
        reservations.add(reservation);
      }
    }
    catch(SQLException e)
    {
      System.err.println("Error retrieving Reservation records from database.");
      System.exit(1);
    }
    
    return reservations.toArray(new Object[reservations.size()][reservations.size()]);
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