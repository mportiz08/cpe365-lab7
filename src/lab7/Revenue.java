package lab7;

import java.sql.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 * Revenue model class.
 * @author Marcus Ortiz
 */
public class Revenue
{ 
  private class BaseTableModel extends AbstractTableModel
  {
    private Object[][] data;
    private String[] cols;

    public BaseTableModel(Object[][] data)
    {
      this.data = data;
      String[] columns = {"Room", "Jan", "Feb", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November",
                          "December", "Total"};
      this.cols = columns;
    }

    public Object getValueAt(int row, int col)
    {
      return this.data[row][col];
    }

    public int getRowCount()
    {
      return data.length;
    }

    @Override
    public String getColumnName(int i)
    {
      return this.cols[i];
    }

    public int getColumnCount()
    {
      return this.cols.length;
    }

    @Override
    public Class getColumnClass(int i)
    {
      return this.cols[i].getClass();
    }
  }

  public static String[] COLS = {"JAN", "FEB", "MAR", "APR", "MAY",
                                 "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
  private Connection conn;
  
  public Revenue(Connection c)
  {
    this.conn = c;
  }
  
  public AbstractTableModel getResTable()
  {
    return new BaseTableModel(getResCounts());
  }
  
  public AbstractTableModel getRevTable()
  {
    return new BaseTableModel(getRevCounts());
  }
  
  private Object[][] getResCounts()
  {
    String sql =
     "SELECT RM.Name, TO_CHAR(RS.CheckIn, 'MON') AS Month, COUNT(*) " +
     "FROM Reservations RS, Rooms RM " +
     "WHERE RS.Room = RM.Id " +
     "GROUP BY Name, TO_CHAR(RS.CheckIn, 'MON') " +
     "ORDER BY Name, Month";
    ArrayList<Object[]> rows = getRows(sql, "res");

    TreeMap<String, ArrayList<Object[]>> data = new TreeMap<String, ArrayList<Object[]>>();

    for(Object[] r : rows)
    {
      String name = (String)r[0];
      String month = (String)r[1];
      Integer res = (Integer)r[2];

      Object[] monthly = new Object[2];
      monthly[0] = month;
      monthly[1] = res;
      if(data.get(name) == null)
      {
        ArrayList<Object[]> monthlies = new ArrayList<Object[]>();
        monthlies.add(monthly);
        data.put(name, monthlies);
      }
      else
      {
        ArrayList<Object[]> monthlies = data.get(name);
        monthlies.add(monthly);
        data.put(name, monthlies);
      }
    }

    ArrayList<Object[]> ret = new ArrayList<Object[]>();
    for(Map.Entry<String, ArrayList<Object[]>> r : data.entrySet())
    {
      ArrayList<Object> row = new ArrayList<Object>();
      row.add(r.getKey());
      int i = 0;
      int total = 0;
      for(String m : COLS)
      {
        Integer res = (Integer)r.getValue().get(i)[1];
        total += res;
        row.add(res);
        i++;
      }
      row.add(total);
      ret.add(row.toArray(new Object[row.size()]));
    }
    return ret.toArray(new Object[ret.size()][rows.size()]);
  }
  
  private Object[][] getRevCounts()
  {
    String sql =
     "SELECT Name, Month, SUM(Revenue) AS Revenue " +
     "FROM (" +
       "SELECT RM.Name, TO_CHAR(RS.CheckIn, 'MON') AS Month, ((RS.CheckOut - RS.CheckIn) * RS.Rate) AS Revenue " +
       "FROM Reservations RS, Rooms RM " +
       "WHERE RS.Room = RM.Id " +
     ") " +
     "GROUP BY Name, Month " +
     "ORDER BY Name, Month";
    ArrayList<Object[]> rows = getRows(sql, "rev");

    TreeMap<String, ArrayList<Object[]>> data = new TreeMap<String, ArrayList<Object[]>>();

    for(Object[] r : rows)
    {
      String name = (String)r[0];
      String month = (String)r[1];
      Double rev = (Double)r[2];
      
      Object[] monthly = new Object[2];
      monthly[0] = month;
      monthly[1] = rev;
      if(data.get(name) == null)
      {
        ArrayList<Object[]> monthlies = new ArrayList<Object[]>();
        monthlies.add(monthly);
        data.put(name, monthlies);
      }
      else
      {
        ArrayList<Object[]> monthlies = data.get(name);
        monthlies.add(monthly);
        data.put(name, monthlies);
      }
    }

    ArrayList<Object[]> ret = new ArrayList<Object[]>();
    for(Map.Entry<String, ArrayList<Object[]>> r : data.entrySet())
    {
      ArrayList<Object> row = new ArrayList<Object>();
      row.add(r.getKey());
      int i = 0;
      double total = 0;
      for(String m : COLS)
      {
        Double rev = (Double)r.getValue().get(i)[1];
        total += rev;
        row.add(rev);
        i++;
      }
      row.add(total);
      ret.add(row.toArray(new Object[row.size()]));
    }
    return ret.toArray(new Object[ret.size()][rows.size()]);
  }
  
  private ArrayList<Object[]> getRows(String sql, String numType)
  {
    ArrayList<Object[]> rows = new ArrayList<Object[]>();
    
    Statement s = null;
    ResultSet results = null;
    try
    {
      s = this.conn.createStatement();
      results = s.executeQuery(sql);

      while(results.next())
      {
        Object[] row = new Object[12];
        row[0] = results.getString(1);
        row[1] = results.getString(2);
        if(numType.equals("rev"))
        {
          row[2] = results.getDouble(3);
        }
        else if(numType.equals("res"))
        {
          row[2] = results.getInt(3);
        }

        rows.add(row);
      }
    }
    catch(SQLException e)
    {
      System.err.println("Error occured when retrieving info from database.");
      System.err.println(sql);
      System.exit(1);
    }
    
    return rows;
  }
}
