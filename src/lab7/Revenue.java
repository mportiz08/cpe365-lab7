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
    ArrayList<Object[]> rows = getRows("CHANGE ME");
    return rows.toArray(new Object[rows.size()][rows.size()]);
  }
  
  private Object[][] getRevCounts()
  {
    ArrayList<Object[]> rows = getRows("CHANGE ME");
    return rows.toArray(new Object[rows.size()][rows.size()]);
  }
  
  private ArrayList<Object[]> getRows(String sql)
  {
    ArrayList<Object[]> rows = new ArrayList<Object[]>();
    
    // TODO
    
    return rows;
  }
}
