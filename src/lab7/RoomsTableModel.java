package lab7;

import javax.swing.table.*;

/**
 * Table model for "Rooms"
 * @author Marcus Ortiz
 */
public class RoomsTableModel extends AbstractTableModel
{
  private Object[][] rooms;
  private String[] cols;
  
  public RoomsTableModel(Object[][] rooms)
  {
    this.rooms = rooms;
    String[] colNames = {"Id", "Name", "Beds", "BedType", "MaxOccupancy",
                 "BasePrice", "Decor"};
    this.cols = colNames;
  }
  
  public Object getValueAt(int row, int col)
  {
    return this.rooms[row][col];
  }
  
  public int getRowCount()
  {
    return rooms.length;
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
