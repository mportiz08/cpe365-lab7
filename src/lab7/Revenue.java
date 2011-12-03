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
    ArrayList<Object[]> rows = getRows("CHANGE ME", "res");
    return rows.toArray(new Object[rows.size()][rows.size()]);
  }
  
  private Object[][] getRevCounts()
  {
    String sql =
     "SELECT Name, Month, SUM(Revenue) AS Revenue" +
     "FROM (" +
       "SELECT RM.Name, TO_CHAR(RS.CheckIn, 'MON') AS Month, ((RS.CheckOut - RS.CheckIn) * RS.Rate) AS Revenue" +
       "FROM Reservations RS, Rooms RM" +
       "WHERE RS.Room = RM.Id" +
     ")" +
     "GROUP BY Name, Month;";
    ArrayList<Object[]> rows = getRows(sql, "rev");
    return rows.toArray(new Object[rows.size()][rows.size()]);
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
    catch(SQLException e)
    {
      System.err.println("Error occured when retrieving info from database.");
      System.exit(1);
    }
    
    return rows;
  }
}
