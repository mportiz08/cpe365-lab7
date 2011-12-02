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
    
    
    public ArrayList<String> getAvailableRooms(String month, int day){
        ArrayList<String> temp = new ArrayList<String>();
        String stmt = "SELECT Name FROM Rooms, Reservations WHERE " +
                "Room = Id AND to_date('" + day + "-" + month + 
                "-10','DD-MM-YY') >= CheckIn AND to_date('" + day + "-" + month +
                "-10','DD-MM-YY') < CheckOut";
        System.out.println(stmt);
        ResultSet results = execute(stmt);
        
        try {
          boolean f = results.next();
          while(f){
             temp.add(results.getString("Name"));     
             f = results.next();
          }
        }
        catch(SQLException ex){
            System.err.println("Error retrieving data from ResultSet");
            System.exit(1);
        }
        
        return temp;
    }
    
    public ArrayList<ArrayList<String>> getAvailableRooms2(String month1, int day1, String month2, int day2){
        //TODO
        ArrayList<ArrayList<String>> temp = null;

        String FullyStmt = "SELECT Name FROM Rooms, Reservations " +
                "WHERE Id = Room AND to_date('" + day1 + "-" + month1 +
                "-10','DD-MM-YY') >= Checkin AND to_date('" + day2 + "-" +
                month2 + "-10','DD-MM-YY') <= Checkout";

        System.out.println(FullyStmt);
        
        /*
         * FULLY OCCUPIED
         * SELECT Roomname from rooms, reservations
         *  WHERE roomid = room AND to_date('23-NOV-10','DD-MM-YY') >= checkin 
         *  to_date('24-NOV-10','DD-MM-YY') <= checkout;
         */

        String PartialStmt = "SELECT Name from Rooms, Reservations " +
                "WHERE Id = Room AND " +
                "to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') > checkin " +
                "AND to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') < checkout " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') >= checkout) " +
                "UNION " +
                "SELECT Name from Rooms, Reservations " +
                "WHERE Id = room AND " +
                "(to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') <= checkin " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') > checkin " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') < checkout)";

        System.out.println(PartialStmt);

        ResultSet full = execute(FullyStmt);
        ResultSet partial = execute(PartialStmt);

        try {
           boolean f = full.next();
           while(f){
               temp.get(0).add(full.getString("Name"));
               f = full.next();
           }

        }
        catch(SQLException ex){
            System.err.println("Error getting data from DB");
            System.exit(1);
        }

        /*
         * PARTIALLY OCCUPIED
         * SELECT roomname from rooms, reservations
         *   WHERE roomid = room AND
         *   (to_date('17-NOV-10','DD-MM-YY') > checkin
         *   AND to_date('17-NOV-10','DD-MM-YY') < checkout
         *   AND to_date('20-NOV-10','DD-MM-YY') >= checkout)
         *   UNION
         *   SELECT roomname from rooms, reservations
         *   WHERE roomid = room AND
         *   (to_date('17-NOV-10','DD-MM-YY') <= checkin
         *   AND to_date('20-NOV-10','DD-MM-YY') > checkin
         *   AND to_date('20-NOV-10','DD-MM-YY') < checkout);
         */
        
        return temp;
    }
}