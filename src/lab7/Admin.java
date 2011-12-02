package lab7;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Admin -- implements the back end database work for AdminPanel
 * @author Marcus Ortiz
 */
public class Admin
{
  public enum Status {FULL, EMPTY, NO_DB};
  
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
  
  public Status getDBStatus()
  {
    Status status = Status.NO_DB;
    
    if(dbExists())
    {
      if(this.getNumRooms() == 0 && this.getNumReservations() == 0)
      {
        status = Status.EMPTY;
      }
      else
      {
        status = Status.FULL;
      }
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
  
  public void clearDB()
  {
    clearTable("Reservations");
    clearTable("Rooms");
  }
  
  public void reloadDB()
  {
    File f = new File("scripts/reload.sql");
    Scanner s = null;
    
    try
    {
      s = new Scanner(f);
    }
    catch(FileNotFoundException e)
    {
      System.err.println("Couldn't find scripts/reload.sql.");
      System.exit(1);
    }
    
    Statement st = null;
    try
    {
      st = this.conn.createStatement();
    }
    catch(SQLException e)
    {
      System.err.println("Error creating statement.");
      System.exit(1);
    }
    
    while(s.hasNextLine())
    {
      String sql = s.nextLine().split(";")[0];
      //update(sql);
      try
      {
        st.addBatch(sql);
      }
      catch(SQLException e)
      {
        System.err.println("Error adding statement to batch.");
        System.exit(1);
      }
    }
    
    try
    {
      st.executeBatch();
    }
    catch(SQLException e)
    {
      System.err.println("Error executing batch statement.");
      System.exit(1);
    }
  }
  
  private boolean dbExists()
  {
    boolean roomsExists = false;
    boolean reservationsExists = false;
    
    try
    {
      roomsExists = query("SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = 'ROOMS'").next();
      reservationsExists = query("SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = 'RESERVATIONS'").next();
    }
    catch(SQLException e)
    {
      System.err.println("SQL error occured.");
      System.exit(1);
    }
    
    return roomsExists && reservationsExists;
  }
  
  private void clearTable(String table)
  {
    try
    {
      Statement s = this.conn.createStatement();
      s.executeUpdate("DELETE FROM " + table);
    }
    catch(SQLException e)
    {
      System.err.println("Error occured when clearing the " + table + " table.");
      System.exit(1);
    }
  }
  
  private void update(String sql)
  {
    try
    {
      Statement s = this.conn.createStatement();
      s.executeUpdate(sql);
    }
    catch(SQLException e)
    {
      System.err.println("Error occured with the following SQL command:");
      System.err.println(sql);
      System.exit(1);
    }
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
      System.err.println("Error occured with the following SQL command:");
      System.err.println(sql);
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
