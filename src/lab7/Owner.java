/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author spartan
 */
public class Owner {
    
    private Connection conn;
    
    public Owner(Connection c) {
        this.conn = c;
    }
    
    /**
     * Executes the passed-in SQL statement
     * @param stmt: SQL statement to be executed
     * @return result: ResultSet
     */
    private ResultSet execute(String stmt){
        ResultSet result = null;
        
        try {
            Statement s1 = this.conn.createStatement();
            result = s1.executeQuery(stmt);
        }
        catch(SQLException ex) {
            System.err.println("Error retrieving from DBBBB");
            System.exit(1);
        }
        return result;
    }
    
    public Object[][] getAllRooms(){
       int i = 0;
       String stmt = "SELECT Name FROM Rooms";
       ArrayList<String> temp = new ArrayList<String>();
       ResultSet results = execute(stmt);
       
       try{
          Boolean f = results.next();
          while(f){
             String s = results.getString("Name");
             temp.add(s);
             i++;
             f = results.next();
          }
      }
      catch(SQLException ex){
         System.err.println("Error retrieving data from ResultSet");
         System.exit(1);
      }
      
      Object[][] rooms = new Object[temp.size()][2];
      for (i = 0; i < 10; i++){
        rooms[i][0] = temp.get(i);  
      }
      return rooms;
    }
    
    
    public void getAvailableRooms(int month, int day){
        String stmt = "SELECT roomname FROM rooms, reservations WHERE " +
                "room = roomid AND to_date('" + day + "-" + month + 
                "-10','DD-MM-YY') = checkin;";
        ResultSet results = execute(stmt);
    }
    
}