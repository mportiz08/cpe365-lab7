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
    
    /**
     * Returns the roomNames of all rooms
     * @return 
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
    
    /**
     * Returns roomnames that are available on the given day
     * @param month
     * @param day
     * @return 
     */
    public ArrayList<String> getAvailableRooms(String month, int day){
        ArrayList<String> temp = new ArrayList<String>();
        String stmt = "SELECT Name FROM Rooms, Reservations WHERE " +
                "Room = Id AND to_date('" + day + "-" + month + 
                "-10','DD-MM-YY') >= CheckIn AND to_date('" + day + "-" + month +
                "-10','DD-MM-YY') < CheckOut";
        
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
    
    /**
     * Returns an array of roomnames which are either fully or partially occupied between the input dates
     * @param month1
     * @param day1
     * @param month2
     * @param day2
     * @return 
     */
    public ArrayList<ArrayList<String>> getAvailableRooms2(String month1, int day1, String month2, int day2){
        //TODO
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();

        String FullyStmt = "SELECT DISTINCT Name FROM Rooms, Reservations " +
                "WHERE Id = Room AND to_date('" + day1 + "-" + month1 +
                "-10','DD-MM-YY') >= Checkin AND to_date('" + day2 + "-" +
                month2 + "-10','DD-MM-YY') <= Checkout";

        
        /*
         * FULLY OCCUPIED
         * SELECT Roomname from rooms, reservations
         *  WHERE roomid = room AND to_date('23-NOV-10','DD-MM-YY') >= checkin 
         *  to_date('24-NOV-10','DD-MM-YY') <= checkout;
         */
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

        String PartialStmt = "SELECT DISTINCT Name from Rooms, Reservations " +
                "WHERE Id = Room AND " +
                "to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') >= checkin " +
                "AND to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') < checkout " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') > checkout " +
                "UNION " +
                "SELECT DISTINCT Name from Rooms, Reservations " +
                "WHERE Id = room AND " +
                "(to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') < checkin " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') >= checkin " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') < checkout) " +
                "UNION " +
                "SELECT DISTINCT Name from Rooms, Reservations " +
                "WHERE Id = room AND " +
                "to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') <= checkin " +
                "AND to_date('" + day1 + "-" + month1 + "-10','DD-MM-YY') < checkout " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') > checkin " +
                "AND to_date('" + day2 + "-" + month2 + "-10','DD-MM-YY') >= checkout";


        ResultSet full = execute(FullyStmt);
        ResultSet partial = execute(PartialStmt);
        ArrayList<String> fulltemp = new ArrayList<String>();
        ArrayList<String> partialtemp = new ArrayList<String>();
        
        try {
           boolean f = full.next();
           while(f){
               fulltemp.add(full.getString("Name"));
               f = full.next();
           }
           
           boolean a = partial.next();
           while(a) {
              partialtemp.add(partial.getString("Name"));
              a = partial.next();
            }
        }
        catch(SQLException ex){
            System.err.println("Error getting data from ResultSet full");
            System.exit(1);
        }
        
        temp.add(fulltemp);
        temp.add(partialtemp);
        
        return temp;
    
    }
    
    /**
     * Returns a reservation code(s) given an SQL statement to execute
     * @param stmt
     * @return 
     */
    public Integer[][] findReservation(String stmt){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int i = 0;
        /*String stmt = "SELECT Code from rooms, reservations WHERE Room = Id" +
                " AND Name = " + "'" + roomName + "'" + " AND to_date('" + day + "-" + month + 
                "-10','DD-MM-YY') >= CheckIn AND to_date('" + day + "-" + month +
                "-10','DD-MM-YY') < CheckOut";
        */
        
        ResultSet results = execute(stmt);
        
        try{
           boolean f = results.next();
           while(f) {
              temp.add(results.getInt("Code"));
              i++;
              f = results.next();
           }
        }
        catch(SQLException ex){
            System.err.println("Error");
            System.exit(1);
        }
        
        Integer[][] reservations = new Integer[temp.size()][1];
        for(i = 0; i < temp.size(); i++){
            reservations[i][0] = temp.get(i);
        }
        
        return reservations;
    }
    
    /**
     * Returns info given a reservation code
     * @param reservation Number
     * @return 
     */
    public Object[][] ReservationInfo(int resNum){
       ArrayList<Object[]> temp = new ArrayList<Object[]>();
       int i = 0;
       String stmt = "SELECT * from reservations WHERE code = " + resNum;
        
       ResultSet results = execute(stmt);
        
       try {
          boolean b = results.next();    
          while(b)
          {
             Integer code = results.getInt("Code");
             String room = results.getString("Room");
             java.sql.Date checkIn = results.getDate("CheckIn");
             java.sql.Date checkOut = results.getDate("CheckOut");
             Double rate = results.getDouble("Rate");
             String lastName = results.getString("LastName");
             String firstName = results.getString("FirstName");
             Integer adults = results.getInt("Adults");
             Integer kids = results.getInt("Kids");
             Object[] res = {code, room, checkIn, checkOut, rate, lastName, firstName, adults, kids};
             
             temp.add(res);
             b = results.next();
          }  
       }
       catch(SQLException e)
       {
          System.err.println("Error retrieving Reservation records from database.");
          System.exit(1);
       }
       
       
       return temp.toArray(new Object[temp.size()][temp.size()]);
  }
    
    public Object[][] getRoomInfo(String roomname){
        
        ArrayList<Object[]> temp = new ArrayList<Object[]>();
        String stmt = "Select * from Rooms where name = " + "'" + roomname + "'";
        ResultSet results = execute(stmt);
        
        System.out.println(stmt);
       try {
          boolean b = results.next();    
          while(b)
          {
             String Id = results.getString("Id");
             String Name = results.getString("Name");
             Integer Beds = results.getInt("Beds");
             String BedType = results.getString("BedType");
             Integer MaxOccupancy = results.getInt("MaxOccupancy");
             Integer BasePrice = results.getInt("BasePrice");
             String Decor = results.getString("Decor");
             Object[] res = {Id, Name, Beds, BedType, MaxOccupancy, BasePrice, Decor};
             
             temp.add(res);
             b = results.next();
          }  
       }
       catch(SQLException e)
       {
          System.err.println("Error retrieving Reservation records from database.");
          System.exit(1);
       }
       return temp.toArray(new Object[temp.size()][temp.size()]);
        
    }
    
}
