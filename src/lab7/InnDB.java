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
  public static Connection getConnection()
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
    
    Connection conn = null;
    try
    {
      conn = DriverManager.getConnection(s.nextLine(), s.nextLine(), s.nextLine());
    }
    catch(SQLException e)
    {
      System.err.println("Failed to connect to database.");
      System.exit(1);
    }
    
    return conn;
  }
}