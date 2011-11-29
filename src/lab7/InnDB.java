package lab7;

import java.util.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author marcus
 */
public class InnDB
{
  private Connection conn;
  
  public InnDB()
  {
    File settings = new File("ServerSettings.txt");
    Scanner s = null;
    
    try
    {
      Class.forName("oracle.jdbc.OracleDriver");
    }
    catch(ClassNotFoundException e)
    {
      System.err.println("Driver failed to load.");
      System.exit(1);
    }
    
    try
    {
      s = new Scanner(settings);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("ServerSettings.txt not found.");
      System.exit(1);
    }
    
    try
    {
      this.conn = DriverManager.getConnection(s.nextLine(), s.nextLine(), s.nextLine());
    }
    catch(SQLException e)
    {
      System.err.println("Failed to connect to database.");
      System.exit(1);
    }
    
    checkForTables();
  }
  
  public Connection getConnection()
  { 
    return this.conn;
  }
  
  private void checkForTables()
  {
    try
    {
      DatabaseMetaData dbm = this.conn.getMetaData();
      
      // Check if "Rooms" table exists in db, make it if not
      System.out.println("Looking for \"Rooms\" table.");
      Statement s = this.conn.createStatement();
      ResultSet results = s.executeQuery("SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = 'ROOMS'");
      if(!results.next())
      {
        System.out.println("Table \"Rooms\" not found.");
        s.executeUpdate("CREATE TABLE Rooms(" +
          "Id           CHAR(3) PRIMARY KEY," +
          "Name         VARCHAR2(50) UNIQUE," +
          "Beds         INT," +
          "BedType      VARCHAR2(10)," +
          "MaxOccupancy INT," +
          "BasePrice    INT," +
          "Decor        VARCHAR2(20)" +
        ")");
      }
      else
      {
        System.out.println("Table \"Rooms\" found.");
      }
      
      // Check if "Reservations" table exists in db, make it if not
      System.out.println("Looking for \"Reservations\" table.");
      results = s.executeQuery("SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = 'RESERVATIONS'");
      if(!results.next())
      {
        System.out.println("Table \"Reservations\" not found.");
        s.executeUpdate("CREATE TABLE Reservations(" +
                          "Code      INT PRIMARY KEY," +
                          "Room      CHAR(3)," +
                          "CheckIn   DATE," +
                          "CheckOut  DATE," +
                          "Rate      FLOAT," +
                          "LastName  VARCHAR2(25)," +
                          "FirstName VARCHAR2(25)," +
                          "Adults    INT CHECK(Adults >= 1)," +
                          "Kids      INT," +
                          "FOREIGN KEY(Room) REFERENCES Rooms," +
                          "CONSTRAINT NoDupes UNIQUE(Room, CheckIn)" +
                        ")");
      }
      else
      {
        System.out.println("Table \"Reservations\" found.");
      }
    }
    catch(SQLException e)
    {
      System.err.println("SQLException occured.");
      System.exit(1);
    }
  }
}
