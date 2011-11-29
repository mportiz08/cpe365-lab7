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
    // TODO
    return 0;
  }
  
  public int getNumReservations()
  {
    // TODO
    return 0;
  }
}
