/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lab7;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author srbecker
 */
public class Guest {

    private Connection conn;

    public Guest(Connection c) {
        this.conn = c;
    }

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
    

/* When a room is selected from the
 * list, the system shall show a detailed information screen, similar to the
 * screen shown to the owner (see OR-4.) The information screen shall contain
 * speciﬁc details about the room, and a ”Check Availability” button.
 */
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
             f = results.next();
          }
      }
      catch(SQLException ex){
         System.err.println("Error retrieving data from ResultSet");
         System.exit(1);
      }

      Object[][] rooms = new Object[temp.size()][2];
      for (i = 0; i < temp.size(); i++){
        rooms[i][0] = temp.get(i);
      }
      return rooms;
    }





}
