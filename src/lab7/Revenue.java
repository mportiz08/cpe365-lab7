package lab7;

import java.sql.*;

/**
 * Revenue model class.
 * @author Marcus Ortiz
 */
public class Revenue
{
  private Connection conn;
  
  public Revenue(Connection c)
  {
    this.conn = c;
  }
}
