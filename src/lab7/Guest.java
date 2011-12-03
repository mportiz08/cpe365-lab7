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
                        System.out.println(stmt);

        }
        catch(SQLException ex) {
            System.err.println("Error retrieving from DBBBB");
            System.out.println(stmt);
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


   public String getWeekday(String m1, String d1){
       ArrayList<Object[]> temp = new ArrayList<Object[]>();
       int i = 0;
       int y = 2010;
       String day = new String();
       String stmt = "SELECT to_char(CheckIn, 'day') as w from reservations " +
                      "WHERE CheckIn = '" + d1 + "-" + m1 + "-10'";

       ResultSet results = execute(stmt);

       try {
          boolean b = results.next();
          while(b)
          {
             day = results.getString("w");
             b = results.next();
          }
       }
       catch(SQLException e)
       {
          System.err.println("Error retrieving Reservation records from database.");
          System.exit(1);
       }


       return day;
  }


   public double getBaseRate(String rm, String m1, String d1, String m2, String d2){
       ArrayList<Object[]> temp = new ArrayList<Object[]>();
       int i = 0;
       int stayLen;
       String th1 = "JANMARMAYJULAUGOCTDEC";
       String th =  "APRJUNSEPNOV";

       double rate = 1;
       Integer num = 0;
       System.out.println("month1/month2:  " + m1 + m2);

        if (m1.equals(m2)) {
            num = Integer.parseInt(d2) - Integer.parseInt(d1);
            System.out.println("num if statement is: " + num);
        }
        else if (th1.contains(m1)) {
             num = 31- Integer.parseInt(d1);
             num = num + Integer.parseInt(d2);
        }
        else if (th.contains(m1)) {
             num = 30- Integer.parseInt(d1);
             num = num + Integer.parseInt(d2);
        }


       /*String stmt = "SELECT (basePrice * (CheckOut-CheckIn)) as r from rooms, reservations " +
                      "WHERE Room = " + "'" + rm + "'" + " AND CheckIn = '" + m1 + "-" + d1 + "-10' AND " +
                      "CheckOut = '" + m2 + "-" + d2 + "-10' AND Room = ID";*/
       String stmt = "SELECT basePrice as r from rooms " +
                      "WHERE Name = " + "'" + rm + "'";

       ResultSet results = execute(stmt);

       try {
          boolean b = results.next();
          while(b)
          {
             rate = results.getDouble("r");
             System.out.println("looprate" + rate + "num:" + num);
             b = results.next();
          }
       }
       catch(SQLException e)
       {
          System.err.println("Error retrieving Reservation records from database.");
          System.exit(1);
       }


       return (rate * num);
  }

}
