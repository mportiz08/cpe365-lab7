package lab7;

import java.sql.*;
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
    Object[][] data = {};
    return new BaseTableModel(data);
  }
  
  public AbstractTableModel getRevTable()
  {
    Object[][] data = {};
    return new BaseTableModel(data);
  }
}
