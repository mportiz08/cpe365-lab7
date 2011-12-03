/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OccupancyPanel.java
 *
 * Created on Nov 30, 2011, 6:48:44 PM
 */
package lab7;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author spartan
 */
public class OccupancyPanel extends javax.swing.JPanel {
    
    /**
     * Private Class for Listening for Row Selections for 1 Date
     */
    private class RoomsSelectionListener implements ListSelectionListener {

        private JTable table;
        
        RoomsSelectionListener(JTable table1) {
           this.table = table1;
        }
        public void valueChanged(ListSelectionEvent e) {
           int row;
           if (table.getRowSelectionAllowed() && !table.getColumnSelectionAllowed() && !e.getValueIsAdjusting()) {
              row = table.getSelectedRow();
              System.out.println("Selected Room: " + RoomsModel.getValueAt(row, 0));
                
              if("Occupied".equals(RoomsModel.getValueAt(row, 1).toString())){
                 int resNumber;
                 String stmt = "SELECT Code from rooms, reservations WHERE Room = Id" +
                " AND Name = " + "'" + RoomsModel.getValueAt(row, 0).toString() + "'" + 
                " AND to_date('" + Integer.parseInt(two.getText()) + "-" + one.getText() + 
                "-10','DD-MM-YY') >= CheckIn AND to_date('" + Integer.parseInt(two.getText()) + "-" + 
                 one.getText() + "-10','DD-MM-YY') < CheckOut";
                 
                 //Object method to find reservation
                 Object[][] reservations = owner.findReservation(stmt);
                 resNumber = Integer.parseInt(reservations[0][0].toString());
                 Object[] ReservationsName = {"Reservation(s)"};
                 ReservationsTable.setModel(new DefaultTableModel(reservations, ReservationsName));
                 
                 //Object method to find reservation details
                 Object[][] info = owner.ReservationInfo(resNumber);
                 Object[] InfoName = {"Code", "Room", "Checkin", "Checkout", "Rate", "Lastname", "Firstname", "Adults", "Kids"};
                 InfoTable.setModel(new DefaultTableModel(info, InfoName));
              }
           }
       }
    }
    
    /**
     * Private Class for Listening for Row Selections for 2 dates
     */
    private class RoomsSelectionListener2 implements ListSelectionListener {

        private JTable table;
        
        RoomsSelectionListener2(JTable table1) {
           this.table = table1;
        }
        public void valueChanged(ListSelectionEvent e) {
           int row;
           if (table.getRowSelectionAllowed() && !table.getColumnSelectionAllowed() && !e.getValueIsAdjusting()) {
              row = table.getSelectedRow();
              System.out.println("Selected Room: " + RoomsModel.getValueAt(row, 0));
                
              if("Fully Occupied".equals(RoomsModel.getValueAt(row, 1).toString())){
                 int resNumber;
                 String FullyStmt = "SELECT Code FROM Rooms, Reservations " +
                "WHERE Id = Room AND Name = " + "'" + RoomsModel.getValueAt(row, 0).toString() + "'" +
                " AND to_date('" + Integer.parseInt(four.getText()) + "-" + three.getText() +
                "-10','DD-MM-YY') >= Checkin AND to_date('" + Integer.parseInt(six.getText()) + "-" +
                five.getText() + "-10','DD-MM-YY') <= Checkout";
                 
                 Object[][] reservations = owner.findReservation(FullyStmt);
                 resNumber = Integer.parseInt(reservations[0][0].toString());
                 Object[] ReservationsName = {"Reservation(s)"};
                 ReservationsTable.setModel(new DefaultTableModel(reservations, ReservationsName));
                 
                 //Object method to find reservation details
                 Object[][] info = owner.ReservationInfo(resNumber);
                 Object[] InfoName = {"Code", "Room", "Checkin", "Checkout", "Rate", "Lastname", "Firstname", "Adults", "Kids"};
                 InfoTable.setModel(new DefaultTableModel(info, InfoName));
              }
              else if("Partially Occupied".equals(RoomsModel.getValueAt(row, 1).toString())){
                 Object[] resNumber;
                 String PartialStmt = "SELECT Code from Rooms, Reservations " +
                    "WHERE Id = Room AND Name = " + "'" + RoomsModel.getValueAt(row, 0).toString() + "'" +
                    " AND to_date('" + Integer.parseInt(four.getText()) + "-" + three.getText() + "-10','DD-MM-YY') >= checkin " +
                    "AND to_date('" + Integer.parseInt(four.getText()) + "-" + three.getText() + "-10','DD-MM-YY') < checkout " +
                    "AND to_date('" + Integer.parseInt(six.getText()) + "-" + five.getText() + "-10','DD-MM-YY') > checkout " +
                    "UNION " +
                    "SELECT Code from Rooms, Reservations " +
                    "WHERE Id = room AND " + "Name = " + "'" + RoomsModel.getValueAt(row, 0).toString() + "'" +
                    " AND to_date('" + Integer.parseInt(four.getText()) + "-" + three.getText() + "-10','DD-MM-YY') < checkin " +
                    "AND to_date('" + Integer.parseInt(six.getText()) + "-" + five.getText() + "-10','DD-MM-YY') >= checkin " +
                    "AND to_date('" + Integer.parseInt(six.getText()) + "-" + five.getText() + "-10','DD-MM-YY') < checkout " +
                    "UNION " +
                    "SELECT Code from Rooms, Reservations " +
                    "WHERE Id = room AND " + "Name = " + "'" + RoomsModel.getValueAt(row, 0).toString() + "'" +
                    " AND to_date('" + Integer.parseInt(four.getText()) + "-" + three.getText() + "-10','DD-MM-YY') <= checkin " +
                    " AND to_date('" + Integer.parseInt(four.getText()) + "-" + three.getText() + "-10','DD-MM-YY') < checkout " +
                    "AND to_date('" + Integer.parseInt(six.getText()) + "-" + five.getText() + "-10','DD-MM-YY') > checkin " +
                    "AND to_date('" + Integer.parseInt(six.getText()) + "-" + five.getText() + "-10','DD-MM-YY') >= checkout";
                 
                 Integer[][] reservations = owner.findReservation(PartialStmt);
                 Object[] ReservationsName = {"Reservations"};
                 ReservationsTable.setModel(new DefaultTableModel(reservations, ReservationsName));
                 
                 //Object method to find reservation details
                 Object[] InfoName = {"Code", "Room", "Checkin", "Checkout", "Rate", "Lastname", "Firstname", "Adults", "Kids"};
                 InfoModel = new DefaultTableModel(new Object[0][0], InfoName);
                 InfoTable.setModel(InfoModel);
                 for(Integer[] i : reservations){
                   InfoModel.addRow(owner.ReservationInfo(i[0].intValue())[0]);
                 }
              }
           }
       }
    }
    

    /** Creates new form OccupancyPanel */
    public OccupancyPanel(Owner owner) {
        this.owner = owner;  
        Object[] columnName = {"Rooms", "Availability"};
        Object[] ReservationsName = {"Reservations"};
        Object[] InfoName = {"Info"};
        RoomsModel = new DefaultTableModel(owner.getAllRooms(), columnName);
        ReservationsModel = new DefaultTableModel(new Object[0][0], ReservationsName);
        InfoModel = new DefaultTableModel(new Object[0][0], InfoName);
        initComponents();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        one = new javax.swing.JTextField();
        two = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        three = new javax.swing.JTextField();
        four = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        five = new javax.swing.JTextField();
        six = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        oneDate = new javax.swing.JButton();
        twoDate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        RoomsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ReservationsTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        InfoTable = new javax.swing.JTable();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 600));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(lab7.Lab7App.class).getContext().getResourceMap(OccupancyPanel.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        one.setText(resourceMap.getString("one.text")); // NOI18N
        one.setName("one"); // NOI18N

        two.setText(resourceMap.getString("two.text")); // NOI18N
        two.setName("two"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        three.setText(resourceMap.getString("three.text")); // NOI18N
        three.setName("three"); // NOI18N

        four.setText(resourceMap.getString("four.text")); // NOI18N
        four.setName("four"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        five.setText(resourceMap.getString("five.text")); // NOI18N
        five.setName("five"); // NOI18N

        six.setText(resourceMap.getString("six.text")); // NOI18N
        six.setName("six"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        oneDate.setText(resourceMap.getString("oneDate.text")); // NOI18N
        oneDate.setName("oneDate"); // NOI18N
        oneDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneDateActionPerformed(evt);
            }
        });

        twoDate.setText(resourceMap.getString("twoDate.text")); // NOI18N
        twoDate.setName("twoDate"); // NOI18N
        twoDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoDateActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        RoomsTable.setModel(RoomsModel);
        RoomsTable.setName("RoomsTable"); // NOI18N
        RoomsTable.setRowHeight(30);
        RoomsTable.setRowMargin(5);
        RoomsTable.setSelectionBackground(resourceMap.getColor("RoomsTable.selectionBackground")); // NOI18N
        jScrollPane1.setViewportView(RoomsTable);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        ReservationsTable.setModel(ReservationsModel);
        ReservationsTable.setName("ReservationsTable"); // NOI18N
        ReservationsTable.setRowHeight(30);
        ReservationsTable.setRowMargin(5);
        ReservationsTable.setSelectionBackground(resourceMap.getColor("ReservationsTable.selectionBackground")); // NOI18N
        jScrollPane2.setViewportView(ReservationsTable);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        InfoTable.setModel(InfoModel);
        InfoTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        InfoTable.setFillsViewportHeight(true);
        InfoTable.setName("InfoTable"); // NOI18N
        InfoTable.setRowHeight(30);
        InfoTable.setRowMargin(5);
        jScrollPane4.setViewportView(InfoTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addComponent(jLabel3))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(404, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(oneDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 660, Short.MAX_VALUE)
                .addComponent(twoDate)
                .addGap(203, 203, 203))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(two, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(six, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jLabel7))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneDate)
                    .addComponent(twoDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

private void oneDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneDateActionPerformed

    System.out.println("Clicked one date button");
    ArrayList<String> AvailableRooms = null;
    String monthString = this.one.getText();
    String dayString = this.two.getText();
    
    if(verifyDates(dayString, monthString)){
        
       AvailableRooms = this.owner.getAvailableRooms(monthString, Integer.parseInt(dayString));
       for(int i = 0; i < RoomsModel.getRowCount(); i++){
          if(AvailableRooms.contains(RoomsModel.getValueAt(i, 0).toString())){
             RoomsModel.setValueAt("Occupied", i, 1);
          }
          else {
            RoomsModel.setValueAt("Empty", i ,1);
         }
       }
    }
    else{
        System.out.println("Invalid date");
        System.exit(1);
    }
    
    RoomsTable.setRowSelectionAllowed(true);
    RoomsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    RoomsTable.getSelectionModel().addListSelectionListener(new RoomsSelectionListener(RoomsTable));
}//GEN-LAST:event_oneDateActionPerformed

private void twoDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoDateActionPerformed
 
    System.out.println("Clicked two date button");
    ArrayList<ArrayList<String>> results = null;
    String monthString1 = this.three.getText();
    String dayString1 = this.four.getText();
    String monthString2 = this.five.getText();
    String dayString2 = this.six.getText();
    
    if(verifyDates(dayString1, monthString1) && verifyDates(dayString2, monthString2)){
        //Call Model method
        results = this.owner.getAvailableRooms2(monthString1, Integer.parseInt(dayString1), monthString2, Integer.parseInt(dayString2));
        for(int i = 0; i < RoomsModel.getRowCount(); i++){
            if(results.get(0).contains(RoomsModel.getValueAt(i, 0).toString())){
                RoomsModel.setValueAt("Fully Occupied", i, 1);
            }
            else if(results.get(1).contains(RoomsModel.getValueAt(i, 0).toString())){
                RoomsModel.setValueAt("Partially Occupied", i, 1);
            }
            else{
                RoomsModel.setValueAt("Empty", i, 1);
            }
        }
    }
    else{
        System.out.println("Invalid date");
        System.exit(1);
    }
    
    RoomsTable.setRowSelectionAllowed(true);
    RoomsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    RoomsTable.getSelectionModel().addListSelectionListener(new RoomsSelectionListener2(RoomsTable));
}//GEN-LAST:event_twoDateActionPerformed

private boolean verifyDates(String dayString, String monthString){
    String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    int day = 0;
    boolean monthFound = false;
    
    //Checking both for nulls + valid month
    if(monthString != null && dayString != null && !monthString.isEmpty() && !dayString.isEmpty()) {
        try{
           day = Integer.parseInt(dayString);
        }
        catch(NumberFormatException ex){
            System.out.println("Day entered is not an integer!!");
            System.exit(1);        
        }
        for(String i : months){
           if(i.equals(monthString)) {
              monthFound = true;
           } 
        }
    }
    
    //Error Checking Dates/Calling model method
    if(day > 0 && day <= 31 && monthFound == true){
        return true;
    }

    return false;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable InfoTable;
    private javax.swing.JTable ReservationsTable;
    private javax.swing.JTable RoomsTable;
    private javax.swing.JTextField five;
    private javax.swing.JTextField four;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField one;
    private javax.swing.JButton oneDate;
    private javax.swing.JTextField six;
    private javax.swing.JTextField three;
    private javax.swing.JTextField two;
    private javax.swing.JButton twoDate;
    // End of variables declaration//GEN-END:variables
    private Owner owner;
    private DefaultTableModel RoomsModel;
    private DefaultTableModel ReservationsModel;
    private DefaultTableModel InfoModel;
}
