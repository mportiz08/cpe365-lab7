package lab7;

import javax.swing.table.*;

/**
 * TableModel for "Reservations"
 * @author Marcus Ortiz
 */
public class ReservationsTableModel extends AbstractTableModel
{
  private Object[][] reservations;
  private String[] cols;
  
  public ReservationsTableModel(Object[][] rooms)
  {
    this.reservations = rooms;
    String[] colNames = {"Code", "Room", "CheckIn", "CheckOut", "Rate",
                         "LastName", "FirstName", "Adults", "Kids"};
    this.cols = colNames;
  }
  
  public Object getValueAt(int row, int col)
  {
    return this.reservations[row][col];
  }
  
  public int getRowCount()
  {
    return reservations.length;
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
